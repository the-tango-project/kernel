package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.PersonaDto;

import reactor.core.publisher.Mono;

public interface PerfilService {

    Mono<Mono<PersonaDto>> getPerfil(String usuarioId);
}
