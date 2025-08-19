package com.gokul.service

import com.gokul.dao.EmployeeDAO
import com.gokul.model.Employee
import javax.ws.rs.BadRequestException

class EmployeeManager(private val employeeDAO: EmployeeDAO) {

    fun getEmployeesList(): List<Employee> {
        return employeeDAO.getAll() // Fetch all employees from DB
    }

    fun getEmployeeById(empId: String): Employee {
        if(empId.isBlank()){
            throw BadRequestException("User id can not be empty")
        }
        return employeeDAO.findById(empId) ?: throw BadRequestException("User with id '$empId' not found")
    }
    // Add other methods later: addEmployee, deleteEmployee
}
