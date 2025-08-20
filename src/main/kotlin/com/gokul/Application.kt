package com.gokul

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.gokul.dao.EmployeeDAO
import com.gokul.resource.AttendanceResource
import com.gokul.resource.EmployeeResource
import com.gokul.service.EmployeeManager
import io.dropwizard.core.Application
import io.dropwizard.core.setup.Bootstrap
import io.dropwizard.core.setup.Environment
import io.dropwizard.jdbi3.JdbiFactory
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin

class Application : Application<Configuration>() {
    override fun initialize(bootstrap: Bootstrap<Configuration>) {
        bootstrap.objectMapper.registerModule(kotlinModule())
        bootstrap.objectMapper.registerModule(JavaTimeModule())
        bootstrap.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    override fun run(configuration: Configuration, environment: Environment) {
        // Setup JDBI
        val factory = JdbiFactory()
        val jdbi: Jdbi = factory.build(environment, configuration.database, "postgresql")
        jdbi.installPlugin(SqlObjectPlugin())
        jdbi.installPlugin(KotlinPlugin())


        //Instead, create your DAO class by passing Jdbi into it:
        val employeeDAO = jdbi.onDemand(EmployeeDAO::class.java)
//        val attendanceService = AttendanceDao(attendanceDao)

        val employeeManager = EmployeeManager(employeeDAO,1009)

 // 4. Register EmployeeResource
        environment.jersey().register(EmployeeResource(employeeManager))
        environment.jersey().register(AttendanceResource(employeeManager))

    }

}


fun main(args: Array<String>) {
    Application().run(*args)
}
