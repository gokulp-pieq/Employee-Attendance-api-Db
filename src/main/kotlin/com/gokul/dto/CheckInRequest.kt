package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank


data class CheckInRequest(
    @JsonProperty("empId")
    @get:NotBlank(message = "empId cannot be empty")
    val empId: String,
    @JsonProperty("checkInDateTime")
    val checkInDateTime: String
)
