# Java-Restful
Java applicaton with restful web service

This is a simple java web application with capability of Restful web services. It uses Javax.ws.rs interfaces and annotations to create RESTFUL service. Its about CRUD operations to instert, update and delete employee information which can be done via REST API client such as POSTMAN(if REST API is not implemented). 

The project contains User Interface listing employee information which is accessible from http://localhost:8080/RESTExampleJava/ when you run the project. 

This JAVA API with Restful service provides following operations including example:

1. POST: insert employee information 
http://localhost:8080/RESTExampleJava/rest/employees/add
Body:
    {
      "address": "helsinki",
      "contactNo": "485497593",
      "employmentType": "part",
      "joinedDate": "545734",
      "name": "jhfs"
    }

2. GET:
http://localhost:8080/RESTExampleJava/rest/employees

3. PUT
http://localhost:8080/RESTExampleJava/rest/employees/3

body:
{
  "address": "juhh",
  "contactNo": "43493853",
  "employmentType": "part",
  "joinedDate": "545734",
  "name": "jhfs"
}

4. DELETE
http://localhost:8080/RESTExampleJava/rest/employees/delete/6







