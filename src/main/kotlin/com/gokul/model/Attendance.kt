package com.gokul.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.Duration

data class Attendance(
    @JsonProperty("emp_id")
    val empId: String,
    @JsonProperty("checkin_datetime")
    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    val checkInDatetime: LocalDateTime,
    @JsonProperty("checkout_datetime")
    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    var checkOutDatetime: LocalDateTime?=null,
    @JsonProperty("working_hrs")
    var workingHrs: Duration= Duration.ZERO
)