package com.yoymico.starter.models.dao;

import com.yoymico.starter.models.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAvatarRepo extends JpaRepository<Avatar, Long> {
}
