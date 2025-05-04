# Spring MVC

1. Configure Spring MVC and DB(in `db.properties` file). Adding Spring dependencies use the same version for each of
   them.
   For this purpose you can use property `spring.version`.
3. Create UserResponseDto with all fields of User in `dto` package.
   Also, you should add default constructor explicitly if you'll decide to create new ones.
4. Create UserController in ``controller`` package where you should
    - implement method ```GET: /users/inject``` which will create test data. Let's create three users in this method and
      save them to DB:
      firstName  | lastName
    ------------- | -------------
      John  | Doe
      Emily  | Stone
      Hugh  | Dane
    - method ```UserResponseDto get(Long userId)```, URL: ```GET: /users/{userId} ```. This method should return
      information about user by user id.
    - method ```List<UserResponseDto> getAll```, URL: ```GET: /users```. This method should return information about all
      users from DB.

In response let's return the message ``Users are injected!``

4. Create a `UserMapper` in `service` package, where we're gonna perform all mapping to/from DTOs in order to stick with
   SRP. Use will use this mapper on the controller layer.

4. Run tests with command `mvn -DskipTests=false clean package`.

__You can check yourself using this
__ [checklist](https://mate-academy.github.io/jv-program-common-mistakes/java-spring/web/java-spring-web)
