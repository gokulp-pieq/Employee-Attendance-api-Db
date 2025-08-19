package com.gokul.dao

import com.gokul.model.Employee
import org.jdbi.v3.sqlobject.statement.SqlQuery

interface EmployeeDAO {

    @SqlQuery(
        """
        SELECT emp_id, first_name, last_name, role_id, dept_id, reporting_to
        FROM employees
        """
    )
    fun getAll(): List<Employee>  // We will map all columns to Employee
}
