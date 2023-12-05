package com.matheus.projetowebapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.matheus.projetowebapi.model.User;
import com.matheus.projetowebapi.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String getImgUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userImg = userRepository.findById(user.getId());
        return userImg.isPresent()?userImg.get().getImg():"";
    }
}
