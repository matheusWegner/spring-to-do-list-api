package com.matheus.projetowebapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.projetowebapi.model.dto.AuthenticationDTO;
import com.matheus.projetowebapi.model.dto.UserDTO;
import com.matheus.projetowebapi.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
	private UserService userService;
  

    @GetMapping("/getImg")
    public ResponseEntity getImgEntity(){
        var img = this.userService.getImgUser();
        return ResponseEntity.ok(new UserDTO(img));
    }
}
