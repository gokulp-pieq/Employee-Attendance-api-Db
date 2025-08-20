package com.gokul.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.time.Duration

data class Attendance(
    val emp_id: String,
    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    val checkin_datetime: LocalDateTime,
    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    var checkout_datetime: LocalDateTime?=null,
    var working_hrs: Duration = Duration.ZERO
)