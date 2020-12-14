package com.yoymico.starter.models.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoymico.starter.IServices.IUsuarioService;
import com.yoymico.starter.models.dao.IPasswordResetTokenDao;
import com.yoymico.starter.models.dao.IUsuarioDao;
import com.yoymico.starter.models.dao.PasswordResetTokenRepository;
import com.yoymico.starter.models.entity.PasswordResetToken;
import com.yoymico.starter.models.entity.Role;
import com.yoymico.starter.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger ( UsuarioService.class );

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IPasswordResetTokenDao passwordResetTokenDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	@Override
	@Transactional( readOnly = true )
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.findByUsername ( username );

		if (usuario == null) {
			logger.error ( "Error en el login: no existe el usuario '" + username + "' en el sistema!" );
			throw new UsernameNotFoundException ( "Error en el login: no existe el usuario '" + username + "' en el sistema!" );
		}

		List<GrantedAuthority> authorities = usuario.getRoles ( ).stream ( ).map ( role -> new SimpleGrantedAuthority ( role.getName ( ) ) )
				.peek ( authority -> logger.info ( "Role: " + authority.getAuthority ( ) ) ).collect ( Collectors.toList ( ) );

		return new User ( usuario.getUsername ( ) , usuario.getPassword ( ) , usuario.getEnabled ( ) , true , true , true , authorities );
	}

	@Override
	@Transactional( readOnly = true )
	public Usuario findByUsername( String username ) {
		return usuarioDao.findByUsername ( username );
	}

	public Usuario saveUser( Usuario usuario ) {

		Role role = roleService.findByName ( "ROLE_USER" );
		List<Role> roles = Arrays.asList ( role );
		usuario.setRoles ( roles );
		usuarioDao.save ( usuario );
		return null;

	}

	@Override
	@Transactional( readOnly = true )
	public Usuario findByEmail( String email ) {
		return usuarioDao.findByEmail ( email );
	}

	@Override
	public void savePasswordResetTokenForUser( Usuario user, String token ) {
		PasswordResetToken myToken = new PasswordResetToken ( token , user );
		passwordResetTokenDao.save ( myToken );
	}

	@Override
	public Usuario getUserByPasswordResetToken( final String token ) {
		return passwordTokenRepository.findByToken ( token ).getUser ( );
	}

	@Override
	public void changeUserPassword( final Usuario usuario, final String password ) {
		usuario.setPassword ( password );
		usuarioDao.save ( usuario );
	}

}
