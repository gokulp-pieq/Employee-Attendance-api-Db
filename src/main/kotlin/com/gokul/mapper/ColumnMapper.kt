package com.gokul.mapper

import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import org.postgresql.util.PGInterval
import java.sql.ResultSet
import java.sql.SQLException
import java.time.Duration

class DurationColumnMapper : ColumnMapper<Duration> {
    @Throws(SQLException::class)
    override fun map(r: ResultSet, columnNumber: Int, ctx: StatementContext?): Duration {
        val interval = r.getObject(columnNumber) as? PGInterval ?: return Duration.ZERO

        return Duration.ofDays(interval.days.toLong())
            .plusHours(interval.hours.toLong())
            .plusMinutes(interval.minutes.toLong())
            .plusSeconds(interval.seconds.toLong())
    }
}

