/**
 * 
 */
package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import com.example.demo.beans.EmployeeDataBean;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Perform different operations on Employees
 * 
 */
@EnableAutoConfiguration
@Path("/employee")
@Api(value="/path")
@Component
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
public class DemoServiceController implements InitializingBean {

	@Autowired
	private EmployeeService employeeService;
	
	/*
	 * URL: http://HOST/app/employee/
	 * 
	 */
	@GET
	@ApiOperation(value = "Find all available employees. ", 
	notes = "Returns relevant information of all employees. ", 
	response = EmployeeDataBean.class,
	responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of information of all employees", response = EmployeeDataBean.class), 
			@ApiResponse(code = 404, message = "No employees found."),
			@ApiResponse(code = 500, message = "Internal server error")})
	public Response getAll() {
		List<EmployeeDataBean> beans = new ArrayList<EmployeeDataBean>();	
		beans = employeeService.findAll();	
		Response response = null;
		if(beans == null){
			String message = "No employee found.";
			response = Response.status(Response.Status.NOT_FOUND).entity((message)).build();
			return response;
		}
		response = Response.ok().entity(beans).build();
		return response;
	}
	
	/*
	 * URL: http://HOST/app/employee/{id}
	 * 
	 */
	@GET @Path("{id}")
	@ApiOperation(value = "Find employee by Id. ", 
	notes = "Returns relevant information of all units. ", 
	response = EmployeeDataBean.class,
	responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of information of particular employee", response = EmployeeDataBean.class), 
			@ApiResponse(code = 404, message = "No employees found."),
			@ApiResponse(code = 500, message = "Internal server error")})
	public Response getEmployeeById(@PathParam("id") Long id) {
		EmployeeDataBean bean = null;
		bean = employeeService.findById(id);	
		Response response = null;
		if(bean == null){
			String message = "No employee found with given ID";
			response = Response.status(Response.Status.NOT_FOUND).entity(message+ ':'+id).build();
			return response;
		}
		response = Response.ok().entity(bean).build();
		return response;
	}
	
	/*
	 * URL: http://HOST/app/employee/
	 * 
	 */
	@POST @Consumes("application/json")
	@ApiOperation(value = "post employee data ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully saved employees to database"), 
			@ApiResponse(code = 404, message = "POST method body is wrong"),
			@ApiResponse(code = 500, message = "Internal server error")})
	public Response saveEmployees(final String empJSON) throws JsonParseException, JsonMappingException, IOException {
		
		Response response = null;
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employeeList = mapper.readValue(empJSON, new TypeReference<List<Employee>>(){});
		Boolean isSaved = new Boolean(false);
		if(employeeList != null && !employeeList.isEmpty()){
			isSaved = employeeService.save(employeeList);

		}
		if(isSaved){
			response = Response.status(Response.Status.OK).entity("data posted successfully!").build();
			return response;
		}
		return null;
	}
	
	@DELETE @Path("{id}")
	@ApiOperation(value = "DELETE employee data ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted employee record"), 
			@ApiResponse(code = 404, message = "POST method body is wrong"),
			@ApiResponse(code = 500, message = "Internal server error")})
	public Response deleteEmployee(@PathParam("id") Long id) {
		Response response = null;
		Boolean isSaved = false;
		if(id != null){
			isSaved = employeeService.delete(id);
		}
		if(isSaved){
			response = Response.status(Response.Status.OK).entity("data deleted successfully!").build();
			return response;
		}
		return null;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
	}

}
