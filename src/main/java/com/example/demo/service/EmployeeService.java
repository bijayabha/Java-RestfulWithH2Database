package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.beans.EmployeeDataBean;
import com.example.demo.model.Employee;

@Service
public interface EmployeeService {
	
	@Transactional
	public Boolean delete(Long id);
	
	@Transactional(readOnly = true)
	public List<EmployeeDataBean> findAll();
	
	@Transactional(readOnly = true)
	public EmployeeDataBean findById(Long id);
	
	@Transactional
	public Boolean save(List<Employee> employeeList);

}
