package com.matheus.projetowebapi.model.dto;

import java.time.LocalDate;

public record TarefaDTO(String descricao, String titulo , LocalDate data , String hora , String id,boolean todoDia) {
}
