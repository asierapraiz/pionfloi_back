package com.yoymico.starter.models.dto;

import com.yoymico.starter.validation.ValidPassword;

public class PasswordDto {

	private String oldPassword;

	private String token;

	@ValidPassword
	private String newPassword;

	public String getOldPassword( ) {
		return oldPassword;
	}

	public void setOldPassword( String oldPassword ) {
		this.oldPassword = oldPassword;
	}

	public String getToken( ) {
		return token;
	}

	public void setToken( String token ) {
		this.token = token;
	}

	public String getNewPassword( ) {
		return newPassword;
	}

	public void setNewPassword( String newPassword ) {
		this.newPassword = newPassword;
	}

}
