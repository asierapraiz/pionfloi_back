package com.yoymico.starter.security;

public interface ISecurityUserService {

	String validatePasswordResetToken( String token );

}
