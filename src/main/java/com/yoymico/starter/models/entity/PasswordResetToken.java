package com.yoymico.starter.models.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "password_reset_token" )
public class PasswordResetToken {

	private static final int EXPIRATION = 60 * 24;

	public PasswordResetToken( ) {
		super ( );
	}

	public PasswordResetToken( final String token ) {
		super ( );

		this.token = token;
		this.expiryDate = calculateExpiryDate ( EXPIRATION );
	}

	public PasswordResetToken( final String token, final Usuario usuario ) {
		super ( );

		this.token = token;
		this.user = usuario;
		this.expiryDate = calculateExpiryDate ( EXPIRATION );
	}

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	private String token;

	@OneToOne( targetEntity = Usuario.class, fetch = FetchType.EAGER )
	@JoinColumn( nullable = false, name = "user_id" )
	private Usuario user;

	private Date expiryDate;

	public Long getId( ) {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getToken( ) {
		return token;
	}

	public void setToken( String token ) {
		this.token = token;
	}

	public Usuario getUser( ) {
		return user;
	}

	public void setUser( Usuario user ) {
		this.user = user;
	}

	public Date getExpiryDate( ) {
		return expiryDate;
	}

	public void setExpiryDate( Date expiryDate ) {
		this.expiryDate = expiryDate;
	}

	private Date calculateExpiryDate( final int expiryTimeInMinutes ) {
		final Calendar cal = Calendar.getInstance ( );
		cal.setTimeInMillis ( new Date ( ).getTime ( ) );
		cal.add ( Calendar.MINUTE , expiryTimeInMinutes );
		return new Date ( cal.getTime ( ).getTime ( ) );
	}

	public void updateToken( final String token ) {
		this.token = token;
		this.expiryDate = calculateExpiryDate ( EXPIRATION );
	}

}
