package com.yoymico.starter.models.dto;

public class MailRequest {

	private String to;
	private String from;
	private String subject;

	public String getTo( ) {
		return to;
	}

	public void setTo( String to ) {
		this.to = to;
	}

	public String getFrom( ) {
		return from;
	}

	public void setFrom( String from ) {
		this.from = from;
	}

	public String getSubject( ) {
		return subject;
	}

	public void setSubject( String subject ) {
		this.subject = subject;
	}

}
