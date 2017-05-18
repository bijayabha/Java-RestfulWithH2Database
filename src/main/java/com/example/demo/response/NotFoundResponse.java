/**
 * 
 */
package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotFoundResponse {
	
	@JsonProperty("NotFoundResponse")
	private String response;
	
	public NotFoundResponse(String response){
		this.response = response;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotFoundResponse [");
		if (response != null)
			builder.append("response=").append(response);
		builder.append("]");
		return builder.toString();
	}

}
