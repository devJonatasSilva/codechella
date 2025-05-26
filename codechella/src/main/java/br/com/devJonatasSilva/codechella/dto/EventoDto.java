package br.com.devJonatasSilva.codechella.dto;

import br.com.devJonatasSilva.codechella.entity.Evento;
import br.com.devJonatasSilva.codechella.enums.TipoEvento;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EventoDto(
        Long id,
        TipoEvento tipo,
        String nome,
        LocalDate data,
        String descricao
) {
    public EventoDto (TipoEvento tipo, String nome) {
        this(null, tipo, nome, null, null);
    }

    public EventoDto (Long id, TipoEvento tipo, String nome) {
        this(id, tipo, nome, null, null);
    }

    public static EventoDto toEvento(Evento evento) {
        return new EventoDto(evento.getId(),evento.getTipo(), evento.getNome());
    }

    public Evento toEntity() {
        return new Evento(id, tipo, nome, data, descricao);
    }
}
