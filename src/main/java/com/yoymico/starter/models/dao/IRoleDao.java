package com.yoymico.starter.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yoymico.starter.models.entity.Role;

public interface IRoleDao extends JpaRepository<Role, Long> {

	public Role findByName( String role );

}
