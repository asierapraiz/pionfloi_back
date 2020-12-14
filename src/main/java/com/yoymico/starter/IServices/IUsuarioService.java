package com.yoymico.starter.IServices;

import com.yoymico.starter.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername( String username );

	public Usuario saveUser( Usuario usuario );

	public Usuario findByEmail( String email );

	public Usuario getUserByPasswordResetToken( String token );

	void changeUserPassword( Usuario usuario, String password );

	void savePasswordResetTokenForUser( Usuario usuario, String token );
}
