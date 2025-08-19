package com.gokul

import com.gokul.dao.EmployeeDAO
import com.gokul.dao.EmployeeRowMapper
import com.gokul.resource.EmployeeResource
import com.gokul.service.EmployeeManager
import io.dropwizard.Application
import io.dropwizard.setup.Environment
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.postgres.PostgresPlugin

class Application : Application<AttendanceConfiguration>() {

    override fun run(configuration: AttendanceConfiguration, environment: Environment) {
        val serialId=1005
        // 1. Create JDBI instance using database config
        val db = configuration.database
        val jdbi = Jdbi.create(db.url, db.user, db.password)
        jdbi.installPlugin(PostgresPlugin())            // optional, for Postgres types
        jdbi.installPlugin(org.jdbi.v3.sqlobject.SqlObjectPlugin())  // REQUIRED for DAO

// 2. Create EmployeeDAO instance
        val employeeDAO = jdbi.onDemand(EmployeeDAO::class.java)

// 3. Create EmployeeManager with DAO
        val employeeManager = EmployeeManager(employeeDAO,serialId)

        jdbi.registerRowMapper(EmployeeRowMapper())


// 4. Register EmployeeResource
        environment.jersey().register(EmployeeResource(employeeManager))

    }
}

fun main(args: Array<String>) {
    Application().run(*args)
}
