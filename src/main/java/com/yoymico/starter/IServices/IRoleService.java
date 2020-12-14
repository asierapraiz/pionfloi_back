package com.yoymico.starter.IServices;

import com.yoymico.starter.models.entity.Role;

public interface IRoleService {

	Role findByName( String role );

}
