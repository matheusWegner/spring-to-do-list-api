package com.matheus.projetowebapi.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.matheus.projetowebapi.model.Tarefa;
import com.matheus.projetowebapi.model.User;
import com.matheus.projetowebapi.model.dto.ResponseDTO;
import com.matheus.projetowebapi.model.dto.TarefaDTO;
import com.matheus.projetowebapi.repository.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public ResponseDTO cadastrar(TarefaDTO tarefaDTO){
        Tarefa tarefa = new Tarefa(tarefaDTO.id(),
                                   tarefaDTO.descricao(),
                                   tarefaDTO.titulo(),
                                   tarefaDTO.data(),
                                   tarefaDTO.hora(),
                                   tarefaDTO.horaFim(),
                                   tarefaDTO.todoDia());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        tarefa.setUserId(user.getId());
        tarefa.setStatus("A");
        tarefaRepository.save(tarefa);
        return new ResponseDTO(true,"salvo com sucesso");
    }

    public ResponseDTO editar(TarefaDTO tarefaDTO){
        Tarefa tarefa = new Tarefa(tarefaDTO.id(),
                                   tarefaDTO.descricao(),
                                   tarefaDTO.titulo(),
                                   tarefaDTO.data(),
                                   tarefaDTO.hora(),
                                   tarefaDTO.horaFim(),
                                   tarefaDTO.todoDia());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        tarefa.setUserId(user.getId());
        tarefa.setStatus("A");
        tarefaRepository.save(tarefa);
        return new ResponseDTO(true,"salvo com sucesso");
    }

    public ResponseDTO remove(String id){
        Optional<Tarefa> optional = tarefaRepository.findById(id);
        if(optional.isPresent()){
            Tarefa tarefa = optional.get();
            tarefa.setStatus("R");
            tarefaRepository.save(tarefa);
        }
        return new ResponseDTO(true,"deletado com sucesso");
    }

    public List<Tarefa> listByUser(String idUser){
        return tarefaRepository.findByUserIdAndStatusNot(idUser,"R");
    }

    public List<Tarefa> listByUserAndData(String idUser,String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(data,formatter);
        return tarefaRepository.findByUserIdAndStatusNotAndDataOrderByHoraAsc(idUser,"R",dateTime);
    }

    public Tarefa listById(String idTarefa){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return tarefaRepository.findByUserIdAndId(user.getId(),idTarefa);
    }
}
