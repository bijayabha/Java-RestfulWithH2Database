package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.beans.EmployeeDataBean;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

public class EmployeeServiceImpl extends SessionServiceImpl implements EmployeeService {

	@Override
	@Transactional
	public Boolean delete(Long id) {
		SQLQuery query = getSession().createSQLQuery(
				"DELETE FROM employee emp WHERE id = :id ");
		query.setParameter("id", id);
		query.executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDataBean> findAll() {
		SQLQuery query = getSession().createSQLQuery(
				"SELECT emp.name as name, emp.address as address, emp.phonenumber as phoneNumber "
						+ "FROM employee emp ");

		List<EmployeeDataBean> beans = new ArrayList<EmployeeDataBean>();
		
		query.addScalar("name",StandardBasicTypes.STRING);
		query.addScalar("address",StandardBasicTypes.STRING);
		query.addScalar("phoneNumber",StandardBasicTypes.LONG);
		
		query.setResultTransformer(Transformers.aliasToBean(EmployeeDataBean.class));
		beans.addAll(query.list());
		return beans;
	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeDataBean findById(Long id) {
		SQLQuery query = getSession().createSQLQuery(
				"SELECT emp.name as name, emp.address as address, emp.phonenumber as phoneNumber "
						+ "FROM employee emp WHERE id = :id ");

		EmployeeDataBean bean = null;
		
		query.setParameter("id", id);
		query.addScalar("name",StandardBasicTypes.STRING);
		query.addScalar("address",StandardBasicTypes.STRING);
		query.addScalar("phoneNumber",StandardBasicTypes.LONG);
		
		query.setResultTransformer(Transformers.aliasToBean(EmployeeDataBean.class));
		bean = (EmployeeDataBean) query.uniqueResult();
		return bean;
	}

	@Override
	@Transactional
	public Boolean save(List<Employee> employees) {
		for(Employee emp: employees){
			getSession().save(emp);
		}
		return true;
	}
}
