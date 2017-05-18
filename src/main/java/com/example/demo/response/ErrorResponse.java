package com.example.demo.response;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends Response {
	
	private String taskName;
	private Date createdOn;
	private Integer statusCode;
	private HttpStatus status;
	private String responseBody;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorResponse [taskName=").append(taskName).append(", createdOn=").append(createdOn)
				.append(", statusCode=").append(statusCode).append(", status=").append(status).append(", responseBody=")
				.append(responseBody).append("]");
		return builder.toString();
	}

	public ErrorResponse(String taskName, Date createdOn, Integer statusCode, HttpStatus status, String responseBody) {
		super();
		this.taskName = taskName;
		this.createdOn = createdOn;
		this.statusCode = statusCode;
		this.status = status;
		this.responseBody = responseBody;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
}
