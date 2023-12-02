package com.matheus.projetowebapi.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.matheus.projetowebapi.model.Tarefa;



@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, String> {

    List<Tarefa> findByUserIdAndStatusNot(String idUser,String status);
    
    List<Tarefa> findByUserId(String idUser);

    Tarefa findByUserIdAndId(String id, String idTarefa);

    public List<Tarefa> findByUserIdAndStatusNotAndDataOrderByHoraAsc(String idUser, String string, LocalDate dateTime);

}
