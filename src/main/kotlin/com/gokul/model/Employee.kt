package com.gokul.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.UUID

//data class Employee(
//    val emp_id: String,
//    val first_name: String,
//    val last_name: String,
//    val role_id: Int,        // Matches role_id in DB
//    val dept_id: Int,        // Matches dept_id in DB
//    val reporting_to: String?
//)
data class Employee(

    @JsonProperty("emp_id")
    val empId: String,

    @get:NotBlank
    @JsonProperty("first_name")
    val firstName: String,

    @get:NotBlank
    @JsonProperty("last_name")
    val lastName: String,

    @JsonProperty("role_id")
    val roleId: Int,

    @JsonProperty("dept_id")
    val deptId: Int,

    @JsonProperty("reporting_to")
    val reportingTo: String? = null

)