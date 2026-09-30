package com.qa.goRest.POJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class User {
	
	private int id;
	private String name;
	private String email;
	
	private String gender;
	private String status;
	
	public User(String name, String email, String gender, String status) {
		
		this.name = name;
		this.email = email;
		this.status = status;
		this.gender = gender;
	}
	
	

}
