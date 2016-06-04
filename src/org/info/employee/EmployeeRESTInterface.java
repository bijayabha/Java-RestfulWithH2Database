package org.info.employee;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employees")
public class EmployeeRESTInterface {
EmployeeDAOImpl dao = new EmployeeDAOImpl();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Employee> findAll() {
		System.out.println("findAll inside java method");
		List<Employee> testdata = dao.findAll();
		for(Employee em: testdata){
			System.out.println("em.name" +em.getName());
		}
		return dao.findAll();
	}

	@GET @Path("search/{query}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Employee> findByName(@PathParam("query") String query) {
		System.out.println("findByName: " + query);
		return dao.findByName(query);
	}

	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee findById(@PathParam("id") String id) {
		System.out.println("findById " + id);
		return dao.findById(Integer.parseInt(id));
	}

	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_JSON})
	public Employee create(Employee employee) {
		System.out.println("inside creating employee");
		return dao.create(employee);
	}

	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public Employee update(Employee employee) {
		System.out.println("Updating employee: " + employee.getName());
		dao.update(employee);
		return employee;
	}
	
	@DELETE 
	@Path("/delete/{id}")
	public void delete(@PathParam("id") int id) {
		dao.delete(id);
	}

}

