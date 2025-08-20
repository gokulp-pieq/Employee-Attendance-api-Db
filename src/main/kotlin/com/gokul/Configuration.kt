package com.gokul


import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.core.Configuration
import io.dropwizard.db.DataSourceFactory
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

class Configuration : Configuration() {

    @Valid
    @NotNull
    @JsonProperty("database")
    var database: DataSourceFactory = DataSourceFactory()
}
