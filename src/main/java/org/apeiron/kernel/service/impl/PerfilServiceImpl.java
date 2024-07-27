package org.apeiron.kernel.service.impl;

import org.apeiron.kernel.service.PerfilService;
import org.apeiron.kernel.service.dto.PersonaDTO;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Override
    public Mono<Mono<PersonaDTO>> getPerfil(String usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPerfil'");
    }

}
