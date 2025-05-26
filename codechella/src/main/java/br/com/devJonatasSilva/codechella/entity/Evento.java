package br.com.devJonatasSilva.codechella.entity;

import br.com.devJonatasSilva.codechella.enums.TipoEvento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("eventos")
@Setter
@Getter
@AllArgsConstructor
public class Evento {
    @Id
    private Long id;
    private TipoEvento tipo;
    private String nome;
    private LocalDate data;
    private String descricao;
}