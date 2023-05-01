# Spring MVC

* Remember about code-style.
* Don't complicate get by id methods in your DAO, use `session.get()`.
* Remember that we return `Optional` only from dao layer, not from service. Be attentive with `session.get()`, it can return null if data is absent.
* [Method names](https://mate-academy.github.io/style-guides/java/java.html#s5.2.3-method-names) are typically verbs or verb phrases.
  For example: `get...`, `put...`, `create...`. Be careful with methods in class `WebConfig`.
* Better use `@GetMapping` instead of `@RequestMapping(value = "/url", method = RequestMethod.GET)` with methods.
  `@GetMapping` is newer and shorter annotation.
* It is a bad practice to use a URL with a type of operation. You should depend on the HTTP method type when implementing endpoints. [Best Practices](https://restfulapi.net/resource-naming/).

  - Wrong:
  ```java
       URL = "/get/{id}"
  ```
  - Good:
  ```java
       URL = "/{id}"
  ```
* You should return `dto` only in methods on the controller layer (don't return it on service or dao layer).
* Create class `UserResponseDto` in separate package `dto`.
* Remember about `id`. Add this field to the response dto.
* Don't pass `User` object in the constructor of `UserResponseDto`.
* Don't create method `mapFromDtoToEntity(...)` in the mapper class. You should only create method that will map data from entity to dto.
* Use Stream API with `UserController::getAll`, for example, while mapping the users to dto objects to get a list of dtos.
* `@GetMapping("/")` is the same that `@GetMapping`.
* Use constructor injection instead of:
```
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private UserService userService = context.getBean(UserService.class);
```
* Don't push jsp file, jsp dependencies, hello controller and `InternalResourceViewResolver resolver()` bean to PR.
* Run checkstyle and fix code style issues.
