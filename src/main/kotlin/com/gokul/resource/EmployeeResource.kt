package com.gokul.resource

import com.gokul.service.EmployeeManager
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class EmployeeResource(private val employeeManager: EmployeeManager) {

    @GET
    @Path("/all")
    fun getAllEmployees(): Response {
        val employees = employeeManager.getEmployeesList()
        return Response.ok(employees).build()
    }

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") empId: String): Response {
        val employee= employeeManager.getEmployeeById(empId)
        return Response.ok(employee).build()
    }
}
