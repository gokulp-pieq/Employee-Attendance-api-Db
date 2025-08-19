package com.gokul.service

import com.gokul.dao.EmployeeDAO
import com.gokul.model.Employee

class EmployeeManager(private val employeeDAO: EmployeeDAO) {

    fun getEmployeesList(): List<Employee> {
        return employeeDAO.getAll() // Fetch all employees from DB
    }

    // Add other methods later: addEmployee, deleteEmployee
}
