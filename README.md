# Rest api in Kotlin for users

This project has as objective to have a rest api build on:

- Gradle
- Kotlin
- Spring boot 2.3.0-RELEASE

Using reactive strategy with webflux and r2dbc (this example is using sql with mysql on this example).

## What the rest api will stand for?

The rest will have the resource:

```
/users
```

Where the endpoints exposed are:

| Method | Endpoint | Description  |
| ---    |:------- |:-----|
|GET| /users | Get all the users |
|POST| /users | Create a new user |
|GET| /users/{user_id} | Get specific user data |
|DELETE| /users/{user_id} | Delete specific user data |
