package org.apeiron.kernel.service.impl;

import org.apeiron.kernel.service.PerfilService;
import org.apeiron.kernel.service.dto.PersonaDto;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Override
    public Mono<Mono<PersonaDto>> getPerfil(String usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPerfil'");
    }

}
