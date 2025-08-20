package com.gokul.dao

import com.gokul.dto.CheckInRequest
import com.gokul.dto.CheckOutRequest
import com.gokul.dto.WorkSummary
import com.gokul.model.Attendance
import com.gokul.model.Employee
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

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
    @SqlQuery("SELECT * FROM employees WHERE emp_id = :emp_id")
    fun findById(@Bind("emp_id") empId: String): Employee?

    //Create Employee
    @SqlUpdate("INSERT INTO employees (emp_id, first_name, last_name, role_id, dept_id,reporting_to) VALUES (:empId, :firstName, :lastName,:roleId, :deptId, :reportingTo)")
    fun insertEmployee(@BindBean employee: Employee): Boolean

    //Remove Employee
    @SqlUpdate("DELETE FROM employees WHERE emp_id = :empId")
    fun deleteEmpById(@Bind("empId") empId: String): Boolean


    //Attendance
//    //Check in
//    @SqlUpdate("""
//        INSERT INTO attendances (emp_id, checkin_datetime)
//        VALUES (:empId, :checkInDatetime)
//    """)
//    fun insertAttendance(@BindBean attendance: Attendance): Boolean
//
//    // Check if user already checked in today
//    @SqlQuery("""
//        SELECT COUNT(*)
//        FROM attendances
//        WHERE emp_id = :empId
//          AND DATE(checkin_datetime) = DATE(:checkInDateTime)
//    """)
//    fun hasAlreadyCheckedIn(
//        @Bind("empId") empId: String,
//        @Bind("checkInDateTime") checkInDateTime: LocalDateTime
//    ): Boolean
//
//    //For validating checkout
//    @SqlQuery("""
//        SELECT emp_id, checkin_datetime,checkout_datetime,working_hrs
//        FROM attendances
//        WHERE emp_id = :empId
//          AND DATE(checkin_datetime) = DATE(:checkOutDateTime)
//          AND checkout_datetime IS NULL
//        ORDER BY checkin_datetime DESC
//        LIMIT 1
//    """)
//    fun validateCheckOut(@BindBean checkOutRequest: CheckOutRequest): Attendance?
//
//    //Checkout
//    @SqlUpdate("""
//        UPDATE attendances
//        SET checkout_datetime = :checkOutDatetime,
//            working_hrs = checkout_datetime-checkin_datetime
//        WHERE emp_id = :empId
//            AND DATE(checkin_datetime)=DATE(:checkInDatetime)
//            AND checkout_datetime IS NULL;
//    """)
//    fun checkOut(
//        @BindBean attendance: Attendance
//    )
//
//    //Get all Attendance entries
//    @SqlQuery(
//        """
//        SELECT emp_id, checkin_datetime, checkout_datetime, working_hrs
//        FROM attendances
//        """
//    )
//    fun getAllAttendance(): List<Attendance>
//
//    //Get all Incomplete Attendance entries
//    @SqlQuery(
//        """
//        SELECT emp_id, checkin_datetime, checkout_datetime, working_hrs
//        FROM attendances
//        WHERE checkout_datetime IS NULL
//        """
//    )
//    fun getAllIncompleteAttendance(): List<Attendance>
//
//    //Get work summary between two days
//    @SqlQuery( """ SELECT emp_id,SUM(checkout_datetime-checkin_datetime) AS working_hrs FROM attendances
//        WHERE DATE(checkout_datetime) BETWEEN DATE(:start) AND DATE(:end)
//        GROUP BY(emp_id); """ )
//    fun summaryOfWorkingHrs(
//        @Bind("start") start: LocalDate,
//        @Bind("end") end: LocalDate
//    ): List<WorkSummary>
}
