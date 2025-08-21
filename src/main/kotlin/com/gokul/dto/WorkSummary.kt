package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Duration

data class WorkSummary(
    @JsonProperty("emp_id")
    val empId: String,
    @JsonProperty("working_hrs")
    var workingHrs: Duration
)
