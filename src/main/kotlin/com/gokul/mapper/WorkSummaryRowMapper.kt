package com.gokul.mapper

//import com.gokul.dto.WorkSummary
//import org.jdbi.v3.core.mapper.RowMapper
//import org.jdbi.v3.core.statement.StatementContext
//import org.postgresql.util.PGInterval
//import java.sql.ResultSet
//import java.time.Duration
//
//class WorkSummaryRowMapper : RowMapper<WorkSummary> {
//    override fun map(rs: ResultSet, ctx: StatementContext): WorkSummary {
//        val interval = rs.getObject("total_working_hrs") as? PGInterval
//        val workingDuration = if (interval != null) {
//            Duration.ofHours(interval.hours.toLong())
//                .plusMinutes(interval.minutes.toLong())
//                .plusSeconds(interval.seconds.toLong())
//        } else {
//            Duration.ZERO
//        }
//
//        return WorkSummary(
//            empId = rs.getString("emp_id"),
//            workingHrs = workingDuration
//        )
//    }
//}
