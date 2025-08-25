# Project Overview
- This project is an Employee Attendance Management System built using Dropwizard with Kotlin.
- It provides RESTful APIs to manage employees and track their attendance (check-in and check-out), while persisting data in a PostgreSQL database through JDBI.
 
# HTTP Request & Response Workflow in Dropwizard

- This project follows a layered architecture where HTTP requests flow through different components before generating a response. Below is the detailed workflow:

---

## Workflow Steps

### 1. Client Request → Jetty Server
- The client sends an HTTP request (e.g.,POST /employee/checkin).  
- Dropwizard runs on Jetty (an embedded HTTP server), which accepts the request.  

### 2. Jetty → Jersey Dispatcher
- Jetty forwards the request to Jersey.  
- Jersey matches the request URL & HTTP method (@GET, @POST, etc.) with the correct Resource class method.  

### 3. Jersey → Resource Layer
- The matched method in the Resource class executes.  
- Request JSON is automatically converted to a Kotlin object using Jackson.  

### 4. Resource → Service Layer
- The Resource calls the Service Class (business logic), which decides how to process the request.  

### 5. Service → DAO Layer
- The Service delegates persistence tasks to the DAO, which interacts with the database using JDBI.  

### 6. DAO → Database → DAO
- DAO executes SQL queries and maps results to model classes.  

### 7. DAO → Service → Resource
- Data flows back upward through DAO → Service → Resource.  

### 8. Resource → Jersey → Client Response
- The Resource returns a response object.  
- Jersey + Jackson serialize the object into JSON.  
- Jetty sends the final HTTP response to the client.

## Client -> Resource -> Service -> DAO -> Service -> Resource -> Client

---
# File description
## DAO
- Interact with the database.
- ### EmployeeDAO
  - Contains all the database interactions related to employees(e.g.,createEmployee, deleteEmployee).
- ### AttendanceDAO
  - Contains all the database interactions related to attendances(e.g.,checkIn, checkOut).
## DTO
- Data Transfer Objects (DTOs) are used to transfer data between client and server.
- They define the structure of requests and responses.
- ### checkInRequest
- ### checkOutRequest
- ### CreateUserRequest
- ### WorkSummary

## Model
- ### Attendance 
  - Data class contains Attendance details such as empId, checkInTime, checkOutTime, workingHrs.
- ### Employee
    - Data class contains Employee details such as empId, name, roleId, deptId, managerId.
- ### Department
  - Enum class contains set of departments.
- ### Role
  - Enum class contains set of roles.

## Resource
- ### AttendanceResource
  - Contains API endpoints related to Attendance.
- ### EmployeeResource
  - Contains API endpoints related to Employee.

## Service
- ### EmployeeService
  - Service class for attendance and employee where business logics happen.

## Application
- Contains Application class and main function, first function(main) called when the application runs.
- Application class contains run methods where we set up jdbi, create obj for employee, attendance dao, register resource class with jersey.

## Configuration
- Configuration class where we get dataSourceFactory details from the config.yml.

## Config.yml
- Contains database details, setup application connection ports.

## BuildGradle.kts
- Contains all the dependencies, setup Application main function location.
---
