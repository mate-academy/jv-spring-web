# Spring MVC

1. Configure Spring MVC and DB(in `db.properties` file)
1. Create UserResponseDto
1. Implement UserController
    - implement method ```GET: /users/inject``` which will create test data. For example, you can create 3 or 4 users in this method and save them to DB.
    - method ```UserResponseDto get(Long userId)```, URL: ```GET: /users/{userId} ```. This method should return information about user by user id.
    - method ```List<UserResponseDto> getAll```, URL: ```GET: /users/```. This method should return information about all users from DB.
1. Create a `UserMapper` in `service` package, where we're gonna perform all mapping to/from DTOs in order to stick with SRP. Use will use this mapper on the controller layer.

__You can check yourself using this__ [checklist](https://mate-academy.github.io/jv-program-common-mistakes/java-spring/web/java-spring-web)
