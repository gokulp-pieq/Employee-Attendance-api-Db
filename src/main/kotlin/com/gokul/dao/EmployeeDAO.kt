package com.gokul.dao

import com.gokul.model.Employee
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface EmployeeDAO {

    @SqlQuery(
        """
        SELECT emp_id, first_name, last_name, role_id, dept_id, reporting_to
        FROM employees
        """
    )
    fun getAll(): List<Employee>  // We will map all columns to Employee

    @SqlQuery("SELECT * FROM employees WHERE emp_id = :emp_id")
    fun findById(@Bind("emp_id") empId: String): Employee?

    @SqlUpdate("INSERT INTO employees (emp_id, first_name, last_name, role_id, dept_id,reporting_to) VALUES (:emp_id, :first_name, :last_name,:role_id, :dept_id, :reporting_to)")
    fun insertEmployee(@BindBean employee: Employee): Boolean
}
