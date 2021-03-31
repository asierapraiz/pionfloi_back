package com.yoymico.starter.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "usuarios" )
public class Usuario implements Serializable {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column( unique = true, length = 20 )
	private String username;

	@Column( length = 60 )
	private String password;

	private Boolean enabled;

	private String nombre;
	private String apellido;

	@Column( unique = true )
	private String email;

	@ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JoinTable( name = "usuarios_roles", joinColumns = @JoinColumn( name = "usuario_id" ), inverseJoinColumns = @JoinColumn( name = "role_id" ), uniqueConstraints = {
			@UniqueConstraint( columnNames = { "usuario_id", "role_id" } ) } )
	private List<Role> roles;

	@JsonIgnoreProperties(value={"avatar", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "avatar_id",  referencedColumnName = "id")
	private Avatar avatar;


	//@JsonIgnoreProperties(value={"tareas", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Reto> retos;

	@NotNull( message = "no puede estar vacio" )
	@Column( name = "create_at" )
	@Temporal( TemporalType.DATE )
	private Date createAt = new Date ( );

	@Column( name = "reset_password_token", length = 30 )
	private String resetPasswordToken;



	public Date getCreateAt( ) {
		return createAt;
	}

	public void setCreateAt( Date createAt ) {
		this.createAt = createAt;
	}

	public Long getId( ) {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getUsername( ) {
		return username;
	}

	public void setUsername( String username ) {
		this.username = username;
	}

	public String getPassword( ) {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public Boolean getEnabled( ) {
		return enabled;
	}

	public void setEnabled( Boolean enabled ) {
		this.enabled = enabled;
	}

	public List<Role> getRoles( ) {
		return roles;
	}

	public void setRoles( List<Role> roles ) {
		this.roles = roles;
	}

	public String getNombre( ) {
		return nombre;
	}

	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	public String getApellido( ) {
		return apellido;
	}

	public void setApellido( String apellido ) {
		this.apellido = apellido;
	}

	public String getEmail( ) {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Reto> getRetos() {
		return retos;
	}

	public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}
}
