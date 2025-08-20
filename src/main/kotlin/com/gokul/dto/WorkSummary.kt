package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import java.time.Duration

data class WorkSummary(
    @get:JsonProperty("empId")
    @get:NotBlank(message = "empId cannot be empty")
    val empId: String,
    var workingHrs: Duration
)
