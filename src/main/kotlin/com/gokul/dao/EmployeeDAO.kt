package com.gokul.dao

import com.gokul.model.Employee
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper

interface EmployeeDAO {

    @SqlQuery("SELECT emp_id, first_name, last_name FROM employees")
    @RegisterBeanMapper(Employee::class)  // Maps SQL result to Employee object
    fun getAll(): List<Employee>
}
