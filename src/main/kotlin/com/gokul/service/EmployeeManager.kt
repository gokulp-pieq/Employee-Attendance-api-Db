package com.gokul.service

import com.gokul.dao.EmployeeDAO
import com.gokul.dto.CreateUserRequest
import com.gokul.model.Employee
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
    // Add other methods later: addEmployee, deleteEmployee
}
