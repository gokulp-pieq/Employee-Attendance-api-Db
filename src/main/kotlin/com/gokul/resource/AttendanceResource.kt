package com.gokul.resource

import com.gokul.dto.CheckInRequest
import com.gokul.dto.CheckOutRequest
import com.gokul.service.EmployeeService
import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/attendances")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AttendanceResource(
    private val employeeService: EmployeeService
) {

    @POST
    @Path("/checkin")
    fun checkIn(@Valid request: CheckInRequest): Response {
        val result = employeeService.checkIn(request)
        return Response.ok(result).build()
    }

    @PUT
    @Path("/checkout")
    fun checkOut(@Valid request: CheckOutRequest): Response {
        val result = employeeService.checkOut(request)
        return Response.ok(result).build()
    }

    @GET
    fun getAttendanceList(): Response {
        val attendances = employeeService.getAttendanceList()
        return Response.ok(attendances).build()
    }

    @GET
    @Path("/workSummary")
    fun getWorkSummary(
        @QueryParam("startDate") startDate: String, @QueryParam("endDate") endDate: String
    ): Response {
        if (startDate.isBlank() || endDate.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("startDate and endDate query parameters are required").build()
        }

        val result = employeeService.getTotalWorkingHrsBetween(startDate, endDate)

        return if (result != null) {
            Response.ok(result).build()
        } else {
            Response.status(Response.Status.BAD_REQUEST).entity("Invalid date format. Please use dd-MM-yyyy").build()
        }
    }

    @GET
    @Path("incomplete")
    fun getIncompleteAttendance(): Response {
        val attendances = employeeService.getIncompleteAttendances()
        return Response.ok(attendances).build()
    }
}