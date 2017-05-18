/**
 * 
 */
package com.example.demo.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionServiceImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		Session session = sessionFactory.getCurrentSession();
		if(session == null){
			session = sessionFactory.openSession();
		}
		return session;
	}

}
