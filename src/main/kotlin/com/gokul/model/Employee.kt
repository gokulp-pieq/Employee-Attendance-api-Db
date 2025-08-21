package com.gokul.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class Employee(

    @JsonProperty("emp_id") val empId: String,

    @get:NotBlank @JsonProperty("first_name") val firstName: String,

    @get:NotBlank @JsonProperty("last_name") val lastName: String,

    @JsonProperty("role_id") val roleId: Int,

    @JsonProperty("dept_id") val deptId: Int,

    @JsonProperty("reporting_to") val reportingTo: String? = null

)