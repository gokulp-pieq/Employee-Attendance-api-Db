package com.gokul.mapper
//
//import com.gokul.model.Employee
//import org.jdbi.v3.core.mapper.RowMapper
//import org.jdbi.v3.core.statement.StatementContext
//import java.sql.ResultSet
//
//class EmployeeRowMapper : RowMapper<Employee> {
//    override fun map(rs: ResultSet, ctx: StatementContext): Employee {
//        return Employee(
//            emp_id = rs.getString("emp_id"),
//            first_name = rs.getString("first_name"),
//            last_name = rs.getString("last_name"),
//            role_id = rs.getInt("role_id"),
//            dept_id = rs.getInt("dept_id"),
//            reporting_to = rs.getString("reporting_to")
//        )
//    }
//}