package com.gokul.model

data class Employee(
    val emp_id: String,
    val first_name: String,
    val last_name: String,
    val role_id: Int,        // Matches role_id in DB
    val dept_id: Int,        // Matches dept_id in DB
    val reporting_to: String?
)
