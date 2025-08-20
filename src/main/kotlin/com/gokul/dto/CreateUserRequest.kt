package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class CreateUserRequest(
    @JsonProperty("firstName")
    @get:NotBlank(message = "firstName cannot be empty")
    val firstName: String,
    @JsonProperty("lastName")
    @get:NotBlank(message = "lastName cannot be empty")
    val lastName: String,
    @JsonProperty("roleId")
    @get:NotNull("role Id cannot be null")
    val roleId: Int,
    @JsonProperty("deptId")
    @get:NotNull("department Id cannot be null")
    val deptId: Int,
    @JsonProperty("reportingTo")
    val reportingTo: String
)
