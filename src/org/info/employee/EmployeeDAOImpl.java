package org.info.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public Employee create(Employee employee) {
		Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO employee (name, address, contactNo, joinedDate, employmentType) VALUES (?, ?, ?, ?, ?)",
                new String[] { "id" });
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getAddress());
            ps.setString(3, employee.getContactNo());
            ps.setString(4, employee.getJoinedDate());
            ps.setString(5, employee.getEmploymentType());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            employee.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return employee;
	}

	@Override
	public Employee update(Employee employee) {
		Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE employee SET name=?, address=?, contactNo=?, joinedDate=?, employmentType=? WHERE id=?");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getAddress());
            ps.setString(3, employee.getContactNo());
            ps.setString(4, employee.getJoinedDate());
            ps.setString(5, employee.getEmploymentType());
            ps.setInt(6, employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return employee;
	}

	@Override
	public boolean delete(int id) {
		Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM employee WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
	}

	@Override
	public List<Employee> findAll() {
		 List<Employee> list = new ArrayList<Employee>();
	        Connection c = null;
	    	String sql = "SELECT * FROM employee ORDER BY name";
	        try {
	            c = ConnectionHelper.getConnection();
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            while (rs.next()) {
	                list.add(processRow(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        for(Employee employee : list){
	        	System.out.println("Employee name:" +employee.getName());
	        }
	        return list;
	}

	@Override
	public List<Employee> findByName(String name) {
		List<Employee> list = new ArrayList<Employee>();
        Connection c = null;
    	String sql = "SELECT * FROM employee as e " +
			"WHERE UPPER(name) LIKE ? " +	
			"ORDER BY name";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
	}

	@Override
	public Employee findById(int id) {
		String sql = "SELECT * FROM employee WHERE id = ?";
        Employee employee = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return employee;
	}

	@Override
	public Employee save(Employee employee) {
		return employee.getId() > 0 ? update(employee) : create(employee);
	}

	@Override
	public Employee processRow(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setAddress(rs.getString("address"));
		employee.setContactNo(rs.getString("contactNo"));
		employee.setJoinedDate(rs.getString("joinedDate"));
		employee.setEmploymentType(rs.getString("employmentType"));       
        return employee;
	}

}
