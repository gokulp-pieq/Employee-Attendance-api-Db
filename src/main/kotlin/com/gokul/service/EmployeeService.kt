package com.gokul.service

import com.gokul.dao.AttendanceDAO
import com.gokul.dao.EmployeeDAO
import com.gokul.dto.CheckInRequest
import com.gokul.dto.CheckOutRequest
import com.gokul.dto.CreateUserRequest
import com.gokul.dto.WorkSummary
import com.gokul.model.Attendance
import com.gokul.model.Employee
import java.time.DateTimeException
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import jakarta.ws.rs.BadRequestException
import java.util.UUID

class EmployeeManager(
    private val employeeDAO: EmployeeDAO,
    private val attendanceDAO: AttendanceDAO
) {

    fun getEmployeesList(): List<Employee> {
        return employeeDAO.getAll() // Fetch all employees from DB
    }

    fun getEmployeeById(empId: UUID): Employee {
        return employeeDAO.findById(empId) ?: throw BadRequestException("User with id '$empId' not found")
    }


    fun addEmployee(empRequest: CreateUserRequest): Employee {
        // Validation

        // Check if employee already exists by unique field like email
        // Create and Insert
        val employee = Employee(
            firstName = empRequest.firstName,
            lastName = empRequest.lastName,
            roleId = empRequest.roleId,
            deptId = empRequest.deptId,
            reportingTo = UUID.fromString(empRequest.reportingTo)
        )
        employeeDAO.insertEmployee(employee)
        return employee
    }

    fun deleteEmployee(empId: UUID) {
        val result = employeeDAO.deleteEmpById(empId)
        if (!result) {
            throw BadRequestException("Failed to delete user. User id not Found")
        }
    }

    fun checkIn(request: CheckInRequest): Attendance {
        if (employeeDAO.findById(request.empId) == null) {
            throw BadRequestException("Employee ID ${request.empId} not found")  //Employee not found with the given id
        }

        val checkInDateTime = validateDateTime(request.checkInDateTime)

        if (attendanceDAO.hasAlreadyCheckedIn(request.empId, checkInDateTime)) {
            throw BadRequestException("User has already checked in today")
        }
        val attendance = Attendance(request.empId, checkInDateTime)
        attendanceDAO.insertAttendance(attendance)
        return attendance
    }

    private fun validateDateTime(inputDateTime: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        return if (inputDateTime.isEmpty()) {
            LocalDateTime.now()
        } else {
            try {
                val dateTime = LocalDateTime.parse(inputDateTime, formatter)
                if (dateTime.isAfter(LocalDateTime.now())) {  //Future date time
                    throw BadRequestException("Future time not allowed")
                } else dateTime
            } catch (e: DateTimeException) {  //Invalid format
                throw BadRequestException("Invalid date time format. Use(yyyy-MM-dd HH:mm)")
            }
        }
    }

    fun checkOut(request: CheckOutRequest): Attendance {
        if (employeeDAO.findById(request.empId) == null) {
            throw BadRequestException("Employee ID ${request.empId} not found")  //Employee not found with the given id
        }

        val checkOutDateTime = validateDateTime(request.checkOutDateTime)

        val attendance: Attendance? = attendanceDAO.validateCheckOut(request.empId,checkOutDateTime)
        if (attendance == null) {
            throw BadRequestException("No valid check-ins yet")    //Invalid check-out
        }
        attendance.checkOutDatetime = checkOutDateTime
        attendance.workingHrs = Duration.between(attendance.checkInDatetime, checkOutDateTime)
        attendanceDAO.checkOut(attendance)  //Valid check out
        return attendance
    }

    fun getAttendanceList(): List<Attendance> {
        return attendanceDAO.getAllAttendance() // Fetch all employees from DB
    }

    fun getIncompleteAttendances(): List<Attendance> {
        return attendanceDAO.getAllIncompleteAttendance()
    }

    //Returns cumulative working hrs of employees between the given dates
    fun getTotalWorkingHrsBetween(startingDate: String, endingDate: String): List<WorkSummary>? {
        val startDate = parseDate(startingDate)
        if (startDate == null) {
            return null
        }

        val endDate = parseDate(endingDate)
        if (endDate == null) {
            return null
        }

        return attendanceDAO.summaryOfWorkingHrs(startDate, endDate)
    }

    fun parseDate(input: String): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            LocalDate.parse(input, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }
}