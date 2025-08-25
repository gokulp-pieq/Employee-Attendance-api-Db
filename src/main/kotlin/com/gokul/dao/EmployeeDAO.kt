package com.gokul.dao

import com.gokul.model.Employee
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.time.LocalDateTime
import java.util.UUID

interface EmployeeDAO {
    //Get all Employees
    @SqlQuery(
        """
        SELECT emp_id, first_name, last_name, role_id, dept_id, reporting_to
        FROM employees
        """
    )
    fun getAll(): List<Employee>  // We will map all columns to Employee

    //Get an employee
    @SqlQuery(
        """
        SELECT emp_id, first_name, last_name, role_id, dept_id, reporting_to
        FROM employees WHERE emp_id = :emp_id
        """
    )
    fun findById(@Bind("emp_id") empId: UUID): Employee?

    //Update Employee
    @SqlUpdate(
        """INSERT INTO employees (emp_id, first_name, last_name, role_id, dept_id,reporting_to) 
        VALUES (:empId, :firstName, :lastName,:roleId, :deptId, :reportingTo)
        """
    )
    fun insertEmployee(@BindBean employee: Employee): Boolean

    //Remove Employee
    @SqlUpdate("DELETE FROM employees WHERE emp_id = :empId")
    fun deleteEmpById(@Bind("empId") empId: UUID): Boolean

    // Check whether a given id is manager or not
    @SqlQuery(
        """
        SELECT EXISTS (
        SELECT 1
        FROM employees 
        WHERE emp_id = :id 
            AND reporting_to is NULL
        );
        """
    )
    fun isManager(
        @Bind("id") id: UUID,
    ): Boolean
}
