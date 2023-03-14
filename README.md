# Management tool

This API acts as a store management tool

***

## Features

- Retrieve all products 
- Retrieve products filtered by id
- Retrieve products filtered by price
- Add a new product
- Update (full/ partial) a product 
- Delete product
- Create users with admin and users roles
***

## Dependencies

- Java SDK 17
- Spring Boot
- Hibernate
- JUnit 
- Spring Security
- Lombok
- Postgres Database- 
- Postman
- Configure the module as Maven project in order to automatically import the required dependencies
***

## API Reference 
## Database connectivity
You should use credentials from application.properties file

## Perform operations on products
In order to perform operations on products you have to be logged in as admin or user so you have to follow the next steps
### Add new roles
##### Json example for postman
{ \
&nbsp; "name": "ADMIN" \
}

```http
  POST /addRole
```
### Add new users
##### Json example for postman

{  \
&nbsp; "username": "john.doe",\
&nbsp; "password": "654321", \
&nbsp; "role": { \
&nbsp; &nbsp; "id": 1, \
&nbsp; &nbsp; "name" : "ADMIN" \
&nbsp; }\
}

```http
  POST /addUser
```

###Login as user
#### In order to test it with Postman you have to enter Authorization tab, choose Basic Auth type and fill Username and Passwords fields

```http
  GET /login
```
#### Login as admin
#### In order to test it with Postman you have to enter Authorization tab, choose Basic Auth type and fill Username and Passwords fields

```http
  GET /admin
```

### Get all products
```http
  GET /api/v1/products
```

### Get product by id

```http
  GET /api/v1/products/${id}
```

### Save new product
#### Only for admins
```http
  POST /api/v1/products
```

### Update a product
#### Only for admins
```http
  PUT /api/v1/products/${id}
```

### Delete product by id
#### Only for admins
```http
  DELETE /api/v1/products/${id}
```

## Run Locally

#### In order to run the application on local machine:

1. Please make sure you are using java 17 version
2. Checkout source code in a directory of your choice using the following command:

```bash
  git clone https://github.com/alex08d/ManagementTool.git
```

3. Open the project with an IDE
4. Open the file `ManagementToolApplication` and click the run button next to the main method to start the
   application
5. After a few seconds, the logs should show that the server has been started

***
## Future possible updates
 - Adding more operations on products depending on various requirements
 - Adding categories for products
 - Add role operation must be performed only by admin, so admin must be logged in as a different entity
***
