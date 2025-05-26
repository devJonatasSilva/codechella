package br.com.devJonatasSilva.codechella.entity;

import br.com.devJonatasSilva.codechella.enums.TipoEvento;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("eventos")
public record Evento (
        @Id
        Long id,
        TipoEvento tipo,
        String nome,
        LocalDate data,
        String descricao
){}