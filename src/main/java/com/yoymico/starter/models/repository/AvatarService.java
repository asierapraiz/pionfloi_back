package com.yoymico.starter.models.repository;

import com.yoymico.starter.IServices.IAvatarService;
import com.yoymico.starter.models.dao.IAvatarRepo;
import com.yoymico.starter.models.entity.Avatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AvatarService implements IAvatarService {

    @Autowired
    private IAvatarRepo avatarRepo;

    @Override
    public Avatar save(Avatar avatar) {
        return avatarRepo.save(avatar);
    }
}
