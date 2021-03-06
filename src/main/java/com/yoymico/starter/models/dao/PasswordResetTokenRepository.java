package com.yoymico.starter.models.dao;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yoymico.starter.models.entity.PasswordResetToken;
import com.yoymico.starter.models.entity.Usuario;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken( String token );

	PasswordResetToken findByUser( Usuario user );

	Stream<PasswordResetToken> findAllByExpiryDateLessThan( Date now );

	void deleteByExpiryDateLessThan( Date now );

	@Modifying
	@Query( "delete from PasswordResetToken t where t.expiryDate <= ?1" )
	void deleteAllExpiredSince( Date now );

}
