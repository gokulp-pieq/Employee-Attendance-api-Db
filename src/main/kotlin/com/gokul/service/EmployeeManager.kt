package com.gokul.service

import com.gokul.dao.EmployeeDAO
import com.gokul.dto.CheckInRequest
import com.gokul.dto.CheckOutRequest
import com.gokul.dto.CreateUserRequest
import com.gokul.model.Attendance
import com.gokul.model.Employee
import java.time.DateTimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.ws.rs.BadRequestException

class EmployeeManager(private val employeeDAO: EmployeeDAO,private var SerialId: Int) {

    fun getEmployeesList(): List<Employee> {
        return employeeDAO.getAll() // Fetch all employees from DB
    }

    fun getEmployeeById(empId: String): Employee {
        if(empId.isBlank()){
            throw BadRequestException("User id can not be empty")
        }
        return employeeDAO.findById(empId) ?: throw BadRequestException("User with id '$empId' not found")
    }


    fun addEmployee(empRequest: CreateUserRequest): Employee {
        // Validation

        // Check if employee already exists by unique field like email
        // Create and Insert
        val employee= Employee("E${SerialId++}",empRequest.firstName,empRequest.lastName,empRequest.roleId,empRequest.deptId,empRequest.reportingTo)
        employeeDAO.insertEmployee(employee)
        return employee
    }

    fun deleteEmployee(empId: String) {
        val result = employeeDAO.deleteEmpById(empId)
        if (!result) {
            throw BadRequestException("Failed to delete user. User id not Found")
        }
    }

    fun checkIn(request: CheckInRequest): Attendance {
        if (employeeDAO.findById(request.empId)==null){
            throw BadRequestException("Employee ID ${request.empId} not found")  //Employee not found with the given id
        }

        val checkInDateTime = validateDateTime(request.checkInDateTime)

        if(employeeDAO.hasAlreadyCheckedIn(request.empId,checkInDateTime)){
            throw BadRequestException("User has already checked in today")
        }
        val attendance= Attendance(request.empId, checkInDateTime)
        employeeDAO.insertAttendance(attendance)
        return attendance
    }

    private fun validateDateTime(inputDateTime: String): LocalDateTime{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        return if (inputDateTime.isEmpty()) {
            LocalDateTime.now()
        } else {
            try {
                val dateTime = LocalDateTime.parse(inputDateTime, formatter)
                if (dateTime.isAfter(LocalDateTime.now())) {  //Future date time
                    throw BadRequestException("Future time not allowed")
                } else dateTime
            } catch (e:DateTimeException) {  //Invalid format
                throw BadRequestException("Invalid date time format. Use(yyyy-MM-dd HH:mm)")
            }
        }
    }

    fun checkOut(request: CheckOutRequest): Attendance{
        if (employeeDAO.findById(request.empId)==null){
            throw BadRequestException("Employee ID ${request.empId} not found")  //Employee not found with the given id
        }

        val checkOutDateTime= validateDateTime(request.checkOutDateTime)

        val attendance: Attendance?= employeeDAO.validateCheckOut(request)
        if(attendance== null){
            throw BadRequestException("No valid check-ins yet")    //Invalid check-out
        }

        employeeDAO.checkOut(attendance.emp_id,attendance.checkin_datetime,checkOutDateTime)  //Valid check out
        return attendance
    }
    // Add other methods later: addEmployee, deleteEmployee
}
