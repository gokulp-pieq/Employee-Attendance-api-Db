package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class CheckOutRequest(
    @get:JsonProperty("empId")
    @get:NotBlank(message = "empId cannot be blank")
    val empId: String = "",
    val checkOutDateTime: String = ""
)