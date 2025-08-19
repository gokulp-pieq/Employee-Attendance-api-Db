package com.gokul.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class CreateUserRequest(
    @get:JsonProperty("firstName")
    @get:NotBlank(message = "firstName cannot be empty")
    val firstName: String="",
    @get:JsonProperty("lastName")
    @get:NotBlank(message = "lastName cannot be empty")
    val lastName: String="",
    @get:NotNull("role Id cannot be null")
    val roleId: Int=-1,
    @get:NotNull("department Id cannot be null")
    val deptId: Int=-1,
    val reportingTo: String=""
)
