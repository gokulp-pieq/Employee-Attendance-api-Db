package com.gokul.resource

import com.gokul.dto.CreateUserRequest
import com.gokul.service.EmployeeManager
import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class EmployeeResource(private val employeeManager: EmployeeManager) {

    @GET
    fun getAllEmployees(): Response {
        val employees = employeeManager.getEmployeesList()
        return Response.ok(employees).build()
    }

    @GET
    @Path("/{id}")
    fun getById(@PathParam("id") empId: String): Response {
        val employee = employeeManager.getEmployeeById(empId)
        return Response.ok(employee).build()
    }

    @POST
    fun addEmployee(@Valid createUserRequest: CreateUserRequest): Response {
        val result = employeeManager.addEmployee(createUserRequest)
        return Response.ok(result).build()
    }

    @DELETE
    @Path("/{empId}")
    fun deleteEmployee(@PathParam("empId") empId: String): Response {
        employeeManager.deleteEmployee(empId)
        return Response.noContent().build()
    }
}
