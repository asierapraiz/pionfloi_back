package com.yoymico.starter.provider;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.yoymico.starter.models.entity.Usuario;

@Service
public class EmailProvider {

	@Autowired
	private JavaMailSender mailSender;

	public SimpleMailMessage constructResetTokenEmail( String contextPath, Locale locale, String token, Usuario user ) {
		//String url = contextPath + "/user/changePassword?token=" + token;

		String url = "http:localhost:4200/user/changePassword?token=" + token;
		//String message = messages.getMessage ( "message.resetPassword" , null , locale );
		String message = " Este enace es válido durante 24 horas. Le servirá pra cambiar su password";
		return constructEmail ( "Reset Password" , message + " \r\n" + url , user );
	}

	public SimpleMailMessage constructEmail( String subject, String body, Usuario user ) {
		SimpleMailMessage email = new SimpleMailMessage ( );
		email.setSubject ( subject );
		email.setText ( body );
		email.setTo ( user.getEmail ( ) );
		//email.setFrom ( env.getProperty ( "support.email" ) );
		email.setFrom ( "asierapraiz@gmail.com" );
		mailSender.send ( email );

		return email;
	}
}
