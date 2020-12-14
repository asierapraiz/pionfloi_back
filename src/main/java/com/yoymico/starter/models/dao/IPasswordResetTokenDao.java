package com.yoymico.starter.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yoymico.starter.models.entity.PasswordResetToken;

public interface IPasswordResetTokenDao extends JpaRepository<PasswordResetToken, Long> {

}
