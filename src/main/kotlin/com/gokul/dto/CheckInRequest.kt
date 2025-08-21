package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID


data class CheckInRequest(
    @JsonProperty("empId")
    val empId: UUID,
    @JsonProperty("checkInDateTime")
    val checkInDateTime: String
)
