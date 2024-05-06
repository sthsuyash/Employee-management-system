# Controllers

- A controller class in a Spring Boot application is responsible for handling incoming HTTP requests and returning
  appropriate HTTP responses.
- It serves as an entry point for processing client requests and often delegates the actual business logic to service
  classes.
    - A controller class is typically annotated with `@RestController` or @Controller.
- Inside the controller class, you define methods that handle specific HTTP requests. These methods are annotated with
  `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, or other similar annotations to
  specify the
  HTTP method and the URL path that the method should respond to.
- Each method in the controller class represents a particular endpoint of the REST API.
- Controller classes often rely on service classes to perform business logic. Dependencies on these service classes are
  typically injected using the @Autowired annotation or constructor injection.
- Controller methods return the response to the client. This can be done by returning a ResponseEntity object to have
  more control over the response status code, headers, and body.

## Autowiring

- Autowired means that Spring will automatically create an instance of <name>Repository and assign it to this variable
- This is called dependency injection
- This is a way to tell Spring that this class is dependent on EmployeeRepository
- Autowire is same as creating an instance of EmployeeRepository and assigning it to this variable but Spring will do
  this for us
- Example:
  ```java
  EmployeeRepository _employeeRepository = new EmployeeRepository();
  ```

## Note:

- The `@PathVariable` annotation is used to extract values from the URI template of the incoming request. E.g.,
  **updateEmployeeById** method.
- The `@RequestParam` annotation is used to extract query parameters from the URL of the incoming request. E.g.,
  **deleteEmployeeById** method.
- The `@RequestBody` annotation is used to extract the request body of the incoming HTTP request. It binds the body of
  the request to a method parameter in a controller method, typically for POST, PUT, and PATCH requests. E.g.,
  **createEmployee** method.
