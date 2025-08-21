# HTTP Request & Response Workflow in Dropwizard

This project follows a layered architecture where HTTP requests flow through different components before generating a response. Below is the detailed workflow:

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

### 4. Resource → Manager/Service Layer
- The Resource calls the Manager (business logic), which decides how to process the request.  

### 5. Manager → DAO Layer
- The Manager delegates persistence tasks to the DAO, which interacts with the database using JDBI.  

### 6. DAO → Database → DAO
- DAO executes SQL queries and maps results to model classes.  

### 7. DAO → Manager → Resource
- Data flows back upward through DAO → Manager → Resource.  

### 8. Resource → Jersey → Client Response
- The Resource returns a response object.  
- Jersey + Jackson serialize the object into JSON.  
- Jetty sends the final HTTP response to the client.  

---
