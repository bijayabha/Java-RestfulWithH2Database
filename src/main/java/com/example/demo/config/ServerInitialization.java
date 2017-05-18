package com.example.demo.config;

public class ServerInitialization extends org.glassfish.jersey.server.ResourceConfig{

	public ServerInitialization(){
		this.packages("com.example.demo.controller");
	}
}
