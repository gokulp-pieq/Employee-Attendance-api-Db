plugins {
    kotlin("jvm") version "2.2.0"
    application
}

application{
    mainClass.set("com.gokul.ApplicationKt")
}
group = "com.gokul"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.named<JavaExec>("run") {
    args("server", "src/main/resources/config.yml")
    jvmArgs = listOf("-Duser.timezone=Asia/Kolkata")
}
dependencies {
    testImplementation(kotlin("test"))
    implementation("io.dropwizard:dropwizard-core:1.3.29")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    implementation(kotlin("stdlib"))
    implementation("org.jdbi:jdbi3-core:3.40.0")
    implementation("org.jdbi:jdbi3-postgres:3.40.0")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("io.dropwizard:dropwizard-db:1.3.29")
    implementation("org.jdbi:jdbi3-sqlobject:3.40.0")


}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}