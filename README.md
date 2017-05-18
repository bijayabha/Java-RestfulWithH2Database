# Java-Restful
Java applicaton with restful web service.

This is a practice program for Restful API.

Technologies used:
Java,
maven,
Hibernate,
Javax.ws.rs,
Spring boot,
PostgreSQL,
Jetty server

How to test the project?

1. Import the project from git repository: https://github.com/bijayabha/Java-RestfulWithPostgreSQL.

2. Default properties file is application.properties where you can set your own postgresql database name. In this project its set as 'db_employee'

2. Change attribute "hbm2ddl.auto" from update -> create in hibernate.cfg.xml file located in src/main/resources so that employee table can be created on-fly when you run the application

3. do mvn-clean and mvn-install

4. Run the project as java application or spring boot application

Now, comes the web service part, 

Any API client can be used to test this. for example: POSTMAN REST client is quite good for this. So, download it, Open it and test the following requests for CRUD operation to employee table.

1. POST: insert employee information                        
http://localhost:8080/RestDemo/app/employee

Request Method: POST
Body:

   [{
	"name": "employee1",
	"address": "Joensuuu",
	"phoneNumber": 4149560862
},
{
	"name": "employee2",
	"address": "Joensuuu",
	"phoneNumber": 41450749985
}]

2. GET:
http://localhost:8080/RestDemo/app/employee
Request Method: GET

3. GET EMPLOYEE BY ID
http://localhost:8080/RestDemo/app/employee/{employeeId}
Request Method: GET


4. DELETE BY ID
http://localhost:8080/RestDemo/app/employee/{employeeId}
Request Method: DELETE
Body:

    {
      "address": "juhh",
      "contactNo": "43493853",
      "employmentType": "part",
      "joinedDate": "545734",
      "name": "jhfs"
    }

For testing purpose, you can use for example: POSTMAN addon from chrome




