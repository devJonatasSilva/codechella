package br.com.devJonatasSilva.codechella.controller;

import br.com.devJonatasSilva.codechella.dto.EventoDto;
import br.com.devJonatasSilva.codechella.entity.Evento;
import br.com.devJonatasSilva.codechella.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoDto> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping("/{id}")
    public Mono<EventoDto> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id);
    }

    @PostMapping
    public Mono<EventoDto> createEvento(@RequestBody EventoDto eventoDto) {
        return eventoService.createEvento(eventoDto);
    }

    @PutMapping("/{id}")
    public Mono<EventoDto> updateEvento(
            @PathVariable Long id,
            @RequestBody EventoDto eventoAtualizado
        ){
        return eventoService.updateEvento(id, eventoAtualizado);
    }

    // @DeleteMapping("/{id}")
    @DeleteMapping("/{id}")
    public Mono<HttpStatus> deleteEvento(@PathVariable Long id) {
        return eventoService.deleteEvento(id);
    }

}
