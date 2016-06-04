# Java-Restful
Java applicaton with restful web service

Hello,
My name is Bijaya Bhandari working as a software developer.

This is a practice program for Restful API.

Technologies used:
Java,
Javax-ws,
HTML,
CSS,
Javascript,
PostgreSQL,
Tomcat8 server

How to test the project?

1. Import the project from git repository: https://github.com/bijayabha/Java-Restful.

2. Create the table named employee in postgreSQL database or any other
CREATE TABLE public.employee
(
  id integer NOT NULL DEFAULT,
  name character varying(100),
  address character varying(100),
  contactno character varying(100),
  joineddate character varying(100),
  employmenttype character varying(100)
)

3.database configuration is stored in file: employee.properties in case change is required.
4. Run the project in tomcat server afer which HTML page having all the employee record get displayed if there is any.

Now, comes the web service part, 

Any API client can be used to test this. for example: POSTMAN plugin from chrome. Open the POSTMAN and test the following requests for CRUD operation to employee table.

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
http://localhost:8080/RESTExampleJava/rest/employees/{id}

body:
{
  "address": "juhh",
  "contactNo": "43493853",
  "employmentType": "part",
  "joinedDate": "545734",
  "name": "jhfs"
}

4. DELETE
http://localhost:8080/RESTExampleJava/rest/employees/delete/{id}




