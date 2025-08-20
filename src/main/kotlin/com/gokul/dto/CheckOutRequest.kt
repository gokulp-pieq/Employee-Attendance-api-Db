package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class CheckOutRequest(
    @JsonProperty("empId")
    @get:NotBlank(message = "empId cannot be blank")
    val empId: String,
    @JsonProperty("checkOutDateTime")
    val checkOutDateTime: String
)