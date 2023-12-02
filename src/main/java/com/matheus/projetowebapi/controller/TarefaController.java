package com.matheus.projetowebapi.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.projetowebapi.model.Tarefa;
import com.matheus.projetowebapi.model.User;
import com.matheus.projetowebapi.model.dto.ResponseDTO;
import com.matheus.projetowebapi.model.dto.TarefaDTO;
import com.matheus.projetowebapi.service.TarefaService;

@RestController
@RequestMapping("/task")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid  TarefaDTO data) throws Exception{
        ResponseDTO response = tarefaService.cadastrar(data);
        return  ResponseEntity.ok().body(response);
    }

    @PostMapping("/edit")
    public ResponseEntity edit(@RequestBody @Valid  TarefaDTO data) throws Exception{
        ResponseDTO response = tarefaService.editar(data);
        return  ResponseEntity.ok().body(response);
    }

    @GetMapping("/remove/{id}")
    public ResponseEntity remove(@PathVariable("id") String id) throws Exception{
        ResponseDTO response = tarefaService.remove(id);
        return  ResponseEntity.ok().body(response);
    
    }

    @GetMapping("/listByUser/{id}")
    public ResponseEntity list(@PathVariable("id") String id) throws Exception{
        List<Tarefa> response = tarefaService.listByUser(id);
        return  ResponseEntity.ok().body(response);
    
    }

    @GetMapping("/listByUserAndData/{data}")
    public ResponseEntity listByUserAndData(@PathVariable("data")  @DateTimeFormat(pattern = "dd-MM-yyyy") String data) throws Exception{
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Tarefa> response = tarefaService.listByUserAndData(user.getId(),data);
        return  ResponseEntity.ok().body(response);
    }

    @GetMapping("/listById/{id}")
    public ResponseEntity listByIdAndUser(@PathVariable("id") String id) throws Exception{
        Tarefa response = tarefaService.listById(id);
        return  ResponseEntity.ok().body(response);
    }

    @PostMapping("/listAll")
    public ResponseEntity listAll(@RequestBody @Valid  TarefaDTO data) throws Exception{
        ResponseDTO response = tarefaService.cadastrar(data);
        return  ResponseEntity.ok().body(response);
    }
}
