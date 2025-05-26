package br.com.devJonatasSilva.codechella.repository;

import br.com.devJonatasSilva.codechella.entity.Evento;
import br.com.devJonatasSilva.codechella.enums.TipoEvento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


public interface EventoRepository extends ReactiveCrudRepository<Evento, Long> {

    Flux<Evento> findByTipo(TipoEvento tipo);

    Mono<Evento> findByNome(String nome);

    Mono<Evento> findById(Long id);

    Flux<Evento> findByDataBetween(LocalDate startDate, LocalDate endDate);

    Mono<Void> deleteById(Long id);

    Mono<Evento> save(Evento evento);
}
