package com.yoymico.starter.models.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yoymico.starter.models.dao.IRoleDao;
import com.yoymico.starter.models.entity.Role;

@Repository
public class RoleService {

	@Autowired
	private IRoleDao roleDao;

	Role findByName( String role ) {
		return roleDao.findByName ( role );
	}

}
