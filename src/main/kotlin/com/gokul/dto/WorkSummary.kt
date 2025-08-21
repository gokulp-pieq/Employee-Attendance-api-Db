package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Duration
import java.util.UUID

data class WorkSummary(
    @JsonProperty("emp_id")
    val empId: UUID,
    @JsonProperty("working_hrs")
    var workingHrs: Duration
)
