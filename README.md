# Kamil Marnik - store

This is a Spring Boot application for an online store with features for creating, listing, and removing orders. 
It's designed to be used by both customers and admins.

## Getting Started

To run this project:
1. Run command: `docker compose up` 
2. You should create a MongoDB database with the name: `store`. 
   You can use for this mongo-express which is available on port: http://localhost:8081/
3. Run spring application.

## API Documentation

After starting Spring application you can check API Documentation under URL: http://localhost:8080/swagger-ui.html

-----------
Main features:
- Create orders
- List orders
- Remove orders

You can also register new users and suppliers with menu.


## Assumptions

I added `security` package to simulate application with token as some kind of authorization, due to requirements in projects (different roles like admin and ordinar customer). 
This is additional package/module just for the simplicity of obtaining token and simulating other microservices.
two main modules are `order` and `supplier`. `order` represents all you asked me to implement, where `supplier` was invented by me to add some kind of logic to this simple application.



## Testing

I use `Spock` and `groovy` to test this application. I wrote unit tests with the usage of Behaviour Driven Development. 
This is why I use `facade` pattern, which is the main class in the module providing the only way to store and obtain data from the module.


