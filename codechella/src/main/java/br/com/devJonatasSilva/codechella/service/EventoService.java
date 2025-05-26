package br.com.devJonatasSilva.codechella.service;

import br.com.devJonatasSilva.codechella.dto.EventoDto;
import br.com.devJonatasSilva.codechella.repository.EventoRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Flux<EventoDto> getAllEventos() {
        return eventoRepository.findAll()
                .map(EventoDto::toEvento);
    }

    public Mono<EventoDto> getEventoById(Long id) {
        return eventoRepository.findById(id)
                .map(EventoDto::toEvento)
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()));
    }

    public Mono<EventoDto> createEvento(EventoDto eventoDto) {
        return eventoRepository.save(eventoDto.toEntity())
                .map(EventoDto::toEvento);
    }

    public Mono<EventoDto> updateEvento(Long id, EventoDto eventoAtualizado) {
        return eventoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()))
                .flatMap(evento -> {
                    evento.setNome(eventoAtualizado.nome());
                    evento.setTipo(eventoAtualizado.tipo());
                    evento.setId(eventoAtualizado.id());
                    evento.setData(eventoAtualizado.data());
                    evento.setDescricao(eventoAtualizado.descricao());
                    return eventoRepository.save(evento);
                })
                .map(EventoDto::toEvento);

    }

    public Mono<HttpStatus> deleteEvento(Long id) {
        return eventoRepository.findById(id)
                .flatMap(evento -> eventoRepository.deleteById(evento.getId())
                        .thenReturn(HttpStatus.NO_CONTENT))
                .switchIfEmpty(Mono.just(HttpStatus.NOT_FOUND));
    }
}
