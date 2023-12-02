package com.matheus.projetowebapi.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TAREFA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ID")
    private String id;
    private String descricao;
    private String titulo;
    private LocalDate data;
    private String hora;
    private String userId;
    private String status;
    private boolean todoDia;
    public Tarefa(String id,String descricao, String titulo, LocalDate data, String hora,boolean todoDia) {
        this.id = id;
        this.descricao = descricao;
        this.titulo = titulo;
        this.data = data;
        this.hora = hora;
        this.todoDia = todoDia;
    }
    
}
