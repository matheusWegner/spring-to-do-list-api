package com.matheus.projetowebapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.projetowebapi.model.User;
import com.matheus.projetowebapi.model.dto.AuthenticationDTO;
import com.matheus.projetowebapi.model.dto.LoginResponseDTO;
import com.matheus.projetowebapi.model.dto.RegisterDTO;
import com.matheus.projetowebapi.model.dto.ResponseDTO;
import com.matheus.projetowebapi.repository.UserRepository;
import com.matheus.projetowebapi.service.TokenService;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
	private AuthenticationManager authManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid  AuthenticationDTO data){
        UsernamePasswordAuthenticationToken usernamePassword = data.converter();
        try {
            var auth = this.authManager.authenticate(usernamePassword);

            String token = this.tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException e) {
            e.printStackTrace();
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(false,"Erro ao autenticar usuário "  + e.getMessage()));
        }
       
    }

    @PostMapping("/register")
    public ResponseEntity cadastro(@RequestBody  RegisterDTO data){
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword);

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
