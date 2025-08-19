package com.gokul.dao

import com.gokul.dto.CheckInRequest
import com.gokul.model.Attendance
import com.gokul.model.Employee
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.time.LocalDateTime

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

    @SqlUpdate("DELETE FROM employees WHERE emp_id = :empId")
    fun deleteEmpById(@Bind("empId") empId: String): Boolean


    //Attendance
    @SqlUpdate("""
        INSERT INTO attendances (emp_id, checkin_datetime) 
        VALUES (:empId, :checkInDateTime)
    """)
    fun insertAttendance(@BindBean attendance: Attendance): Boolean

    // Check if user already checked in today
    @SqlQuery("""
        SELECT COUNT(*) 
        FROM attendances 
        WHERE emp_id = :empId 
          AND DATE(checkin_datetime) = DATE(:checkInDateTime)
    """)
    fun hasAlreadyCheckedIn(
        @Bind("empId") empId: String,
        @Bind("checkInDateTime") checkInDateTime: LocalDateTime
    ): Boolean

}
