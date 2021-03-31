package com.yoymico.starter.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.yoymico.starter.models.entity.Avatar;
import com.yoymico.starter.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yoymico.starter.IServices.IUsuarioService;
import com.yoymico.starter.models.entity.Usuario;
import com.yoymico.starter.models.repository.EmailService;
import com.yoymico.starter.security.ISecurityUserService;

@CrossOrigin( origins = { "http://localhost:4200" } )
@RestController
@RequestMapping( "/api" )
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ISecurityUserService securityUserService;

	@Autowired
	private EmailService mailService;

	@ GetMapping ( "/usuarios" )
	public List<Usuario> index( ) {
		return usuarioService.findAll ( );
	}

	@PostMapping( "/usuario" )
	public ResponseEntity<?> create( @RequestBody Usuario usuario, BindingResult result ) {

		Avatar avatar = new Avatar(null, null, null, null, null, null, null);

		usuario.setAvatar(avatar);
		Usuario nuevoUsuario = usuario;
		nuevoUsuario.setPassword ( passwordEncoder.encode ( usuario.getPassword ( ) ) );
		nuevoUsuario.setEnabled ( true );
		Map<String, Object> response = new HashMap<> ( );

		if (result.hasErrors ( )) {

			List<String> errors = result.getFieldErrors ( ).stream ( ).map ( err -> "El campo '" + err.getField ( ) + "' " + err.getDefaultMessage ( ) ).collect ( Collectors.toList ( ) );

			response.put ( "errors" , errors );
			return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.BAD_REQUEST );
		}

		try {
			usuarioService.saveUser ( nuevoUsuario );
		} catch (DataAccessException e) {
			response.put ( "mensaje" , "Error al realizar el insert en la base de datos" );
			response.put ( "error" , e.getMessage ( ).concat ( ": " ).concat ( e.getMostSpecificCause ( ).getMessage ( ) ) );
			return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.INTERNAL_SERVER_ERROR );
		}

		response.put ( "mensaje" , "El usuario ha sido creado con éxito!" );
		response.put ( "usuario" , nuevoUsuario );

		//this.resetPassword ( );

		return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.CREATED );

	}

	@GetMapping( "/usuario/resetPassword/{email}" )
	public ResponseEntity<?> resetPassword( HttpServletRequest request, @PathVariable String email ) {

		Map<String, Object> response = new HashMap<> ( );

		Usuario usuario = usuarioService.findByEmail ( email );
		if (usuario == null) {

			response.put ( "mensaje" , "Esta direccion de correo no se encuentra en nuestraos registros" );
			return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.NOT_FOUND );
		}

		String token = UUID.randomUUID ( ).toString ( );
		usuarioService.savePasswordResetTokenForUser ( usuario , token );
		//String urlWithToken = constructResetTokenUrl ( request.getRequestURI ( ) , request.getLocale ( ) , token , usuario );
		String urlWithToken = "http://localhost:4200/user/changePassword?token=" + token;
		System.out.println ( urlWithToken );
		//SimpleMailMessage mail = constructEmail ( "Reset Password" , urlWithToken , usuario );
		mailService.sendMail ( usuario , urlWithToken );

		response.put ( "message.resetPasswordEmail" , "revise su correo " );

		return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.OK );
	}

	@GetMapping( "/usuario/changePassword" )
	public ResponseEntity<?> savePassword( final Locale locale, @RequestParam String token, @RequestParam String newPassword ) {

		Map<String, Object> response = new HashMap<> ( );

		String result = securityUserService.validatePasswordResetToken ( token );

		if (result != null) {
			response.put ( "error" , " Formato de password no válido." );
			return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.OK );
		}

		Usuario usuario = usuarioService.getUserByPasswordResetToken ( token );
		if (usuario != null) {
			usuarioService.changeUserPassword ( usuario , passwordEncoder.encode ( newPassword ) );
			response.put ( "mensaje " , " Password modificado correctamente." );
			return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.OK );
		} else {
			response.put ( "mensaje" , "Error al realizar el cacmbio de password" );
			return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}


	/*
		//@GetMapping( "/sendMail" )
		public MailResponse sendMail( @RequestBody MailRequest request ) {
			Map<String, Object> mailModel = new HashMap<> ( );
			mailModel.put ( "name" , "Asier" );
			mailModel.put ( "target" , "www.pinfloi.com" );
	
			return mailService.sendMail ( request , mailModel );
	
		}*/

}
