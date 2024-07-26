package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.PersonaDTO;

import reactor.core.publisher.Mono;

public interface PerfilService {

    Mono<Mono<PersonaDTO>> getPerfil(String usuarioId);
}
