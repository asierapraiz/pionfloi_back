package com.yoymico.starter.models.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.yoymico.starter.models.dto.MailResponse;
import com.yoymico.starter.models.entity.Usuario;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private Configuration freemarkerConfig;

	public MailResponse sendMail( Usuario usuario, String url ) {

		Map<String, Object> mailModel = new HashMap<> ( );

		mailModel.put ( "name" , usuario.getNombre ( ) );
		mailModel.put ( "apellido" , usuario.getApellido ( ) );
		mailModel.put ( "url" , url );

		MailResponse response = new MailResponse ( );
		MimeMessage message = emailSender.createMimeMessage ( );
		try {
			MimeMessageHelper helper = new MimeMessageHelper ( message , MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED , StandardCharsets.UTF_8.name ( ) );

			//helper.addAttachment ( "logo.png" , new ClassPathResource ( "pinfloi_logo.png" ) );

			Template t = freemarkerConfig.getTemplate ( "recupera_pass.ftl" );
			String html = FreeMarkerTemplateUtils.processTemplateIntoString ( t , mailModel );

			helper.setTo ( usuario.getEmail ( ) );
			helper.setText ( html , true );
			helper.setSubject ( "Restableciomiento de password" );
			helper.setFrom ( "www.pinfloi.com" );

			emailSender.send ( message );

			response.setMessage ( "Mail send to:" + usuario.getEmail ( ) );
			response.setStatus ( Boolean.TRUE );

		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage ( "Mail sending failure:" + e.getMessage ( ) );
			response.setStatus ( Boolean.FALSE );
		}

		return response;
	}

}