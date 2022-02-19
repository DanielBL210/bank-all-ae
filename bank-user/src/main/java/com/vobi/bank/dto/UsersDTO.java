package com.vobi.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
	
	
	@NotNull
	@Email
	private String userEmail;

	@NotNull
	private Integer userType;


	@NotNull
	private String enable;

	@NotNull
	private String name;
	
	
	private String token;	

}
