package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern


data class CreateUserRequest(
    @JsonProperty("firstName")
    @get:NotBlank(message = "FirstName cannot be empty")
    val firstName: String,
    @JsonProperty("lastName")
    @get:NotBlank(message = "LastName cannot be empty")
    val lastName: String,
    @JsonProperty("role")
    @get:NotBlank(message = "Role cannot be empty")
    val role: String,
    @JsonProperty("dept")
    @get:NotBlank(message = "Department cannot be empty")
    val dept: String,
    @JsonProperty("reportingTo")
    @get:Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Invalid UUID format"
    )
    val reportingTo: String
)
