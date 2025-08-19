package com.gokul.dao

import com.gokul.model.Attendance
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet
import java.time.Duration
import org.postgresql.util.PGInterval

class AttendanceRowMapper : RowMapper<Attendance> {
    override fun map(rs: ResultSet, ctx: StatementContext): Attendance {
        val interval = rs.getObject("working_hrs") as? PGInterval
        val workingDuration = if (interval != null) {
            Duration.ofHours(interval.hours.toLong())
                .plusMinutes(interval.minutes.toLong())
                .plusSeconds(interval.seconds.toLong())
        } else {
            Duration.ZERO
        }

        return Attendance(
            emp_id = rs.getString("emp_id"),
            checkin_datetime = rs.getTimestamp("checkin_datetime").toLocalDateTime(),
            checkout_datetime = rs.getTimestamp("checkout_datetime")?.toLocalDateTime(),
            working_hrs = workingDuration
        )
    }
}
