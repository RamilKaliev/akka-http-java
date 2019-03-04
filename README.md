## akka-http-java

#### Sample of akka-http written on Java


#### Endpoints (http://localhost/v1/bookstore)

#### Authors:
- [POST] /authors

Request (application/json): 
```json
{
    "firstName": "John",
    "lastName": "Doe"
}
```

Response (application/json):
```json
{
    "firstName": "John",
    "id": 0,
    "lastName": "Doe"
}
```

- [GET] /authors

Response (application/json):
```json
[
    {
        "firstName": "Arthur",
        "id": 2,
        "lastName": "Conan-Doyle"
    },
    {
        "firstName": "John",
        "id": 0,
        "lastName": "Doe"
    },
    {
        "firstName": "Charles",
        "id": 1,
        "lastName": "Dickens"
    },
    {
        "firstName": "Michail",
        "id": 4,
        "lastName": "Lermontov"
    },
    {
        "firstName": "Alex",
        "id": 3,
        "lastName": "Pushkin"
    },
    {
        "firstName": "Joseph",
        "id": 5,
        "lastName": "Cronin"
    }
]
``` 


#### Book path
- [POST] /books

Request (application/json): 
```json
{
    "name": "Mocking",
    "descr": "(unspecified)",
    "authorId": 0
}
```

Response (application/json):
```json
{
    "authorId": 0,
    "descr": "(unspecified)",
    "id": "f84526d2-0554-4210-a202-eccca0bacd7f",
    "name": "Mocking"
}
```

- [GET] /books

Response (application/json):
```json
[
    {
        "authorId": 3,
        "descr": "(unspecified)",
        "id": "7583c3f8-5832-4918-ad87-4c1afa863bb2",
        "name": "Some poetry"
    },
    {
        "authorId": 5,
        "descr": "(unspecified)",
        "id": "6a874dc8-9965-4a89-8034-af1a26629541",
        "name": "The Citadel"
    },
    {
        "authorId": 2,
        "descr": "(unspecified)",
        "id": "4992fa40-7b22-449c-9946-15beeabff02d",
        "name": "The sign of Four"
    },
    {
        "authorId": 0,
        "descr": "(unspecified)",
        "id": "f84526d2-0554-4210-a202-eccca0bacd7f",
        "name": "Mocking"
    },
    {
        "authorId": 1,
        "descr": "(unspecified)",
        "id": "34f2a87a-8db4-40b0-bdb7-79f2b493f4ef",
        "name": "The great expectations"
    },
    {
        "authorId": 4,
        "descr": "(unspecified)",
        "id": "5ca1bf0a-0619-4df4-94ff-3cc967844644",
        "name": "Borodino"
    }
]
```