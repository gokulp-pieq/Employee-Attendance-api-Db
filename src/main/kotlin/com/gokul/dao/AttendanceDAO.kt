package com.gokul.dao

import com.gokul.dto.WorkSummary
import com.gokul.model.Attendance
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

interface AttendanceDAO {
    //Check in
    @SqlUpdate(
        """
        INSERT INTO attendances (emp_id, checkin_datetime) 
        VALUES (:empId, :checkInDatetime)
        """
    )
    fun insertAttendance(@BindBean attendance: Attendance): Boolean

    // Check if user already checked in today
    @SqlQuery(
        """
        SELECT EXISTS (
        SELECT 1
        FROM attendances 
        WHERE emp_id = :empId 
            AND DATE(checkin_datetime) = DATE(:checkInDateTime)
        );
        """
    )
    fun hasAlreadyCheckedIn(
        @Bind("empId") empId: UUID,
        @Bind("checkInDateTime") checkInDateTime: LocalDateTime
    ): Boolean

    //For validating checkout
    @SqlQuery(
        """
        SELECT emp_id, checkin_datetime,checkout_datetime,working_hrs
        FROM attendances 
        WHERE emp_id = :empId 
            AND DATE(checkin_datetime) = DATE(:checkOutDateTime)
            AND checkout_datetime IS NULL;
        """
    )
    fun validateCheckOut(
        @Bind("empId") empId: UUID,
        @Bind("checkOutDateTime") checkOutDateTime: LocalDateTime
    ): Attendance?

    //Checkout
    @SqlUpdate(
        """
        UPDATE attendances
        SET checkout_datetime = :checkOutDatetime,
            working_hrs = :checkOutDatetime-checkin_datetime
        WHERE emp_id = :empId
            AND DATE(checkin_datetime)=DATE(:checkInDatetime)
            AND checkout_datetime IS NULL;
        """
    )
    fun checkOut(
        @BindBean attendance: Attendance
    )

    //Get all Attendance entries
    @SqlQuery(
        """
        SELECT emp_id, checkin_datetime, checkout_datetime, working_hrs
        FROM attendances
        """
    )
    fun getAllAttendance(): List<Attendance>

    //Get all Incomplete Attendance entries
    @SqlQuery(
        """
        SELECT emp_id, checkin_datetime, checkout_datetime, working_hrs
        FROM attendances
        WHERE checkout_datetime IS NULL
        """
    )
    fun getAllIncompleteAttendance(): List<Attendance>

    //Get work summary between two days
    @SqlQuery(
        """
        SELECT emp_id,SUM(working_hrs) AS working_hrs FROM attendances
        WHERE DATE(checkin_datetime) BETWEEN DATE(:start) AND DATE(:end) 
        GROUP BY(emp_id); 
        """
    )
    fun summaryOfWorkingHrs(
        @Bind("start") start: LocalDate,
        @Bind("end") end: LocalDate
    ): List<WorkSummary>
}