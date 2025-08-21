package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class CheckOutRequest(
    @JsonProperty("empId")
    val empId: UUID,
    @JsonProperty("checkOutDateTime")
    val checkOutDateTime: String
)