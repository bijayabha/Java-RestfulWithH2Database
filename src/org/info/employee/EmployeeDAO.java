package org.info.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
	public Employee create(Employee employee);
	public Employee update(Employee employee);
	public boolean delete(int id);
	public List<Employee> findAll();
	public List<Employee>findByName(String name);
	public Employee findById(int id);
	public Employee save(Employee employee);
	public Employee processRow(ResultSet rs) throws SQLException;

}
