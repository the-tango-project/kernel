package org.apeiron.kernel.service.dto.factories;

import feign.FeignException;
import java.util.ArrayList;
import java.util.List;
import org.apeiron.kernel.service.dto.BulkDataProcessResultDto;
import org.apeiron.kernel.service.dto.BulkResponseDto;
import org.apeiron.kernel.service.dto.RuleDto;
import org.apeiron.kernel.service.dto.SolicitudDto;
import org.apeiron.kernel.service.dto.TransicionContextDto;
import org.apeiron.kernel.service.exception.RuleExceptionFactory;
import org.apeiron.kernel.service.exception.RulesException;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

public class BulkDataFactory {

    private BulkDataFactory() {}

    /**
     * Crea un objecto de tipo BulkDataProcessResultDto a partir de un objecto
     * TransicionContextDto para operaciones que han resultado exitosas
     *
     * @param contexto
     * @return BulkDataProcessResultDto
     */
    public static BulkDataProcessResultDto success(TransicionContextDto contexto) {
        return BulkDataProcessResultDto
            .builder()
            .success(true)
            .solicitudId(contexto.getSolicitudId())
            .destino(contexto.getTransicion().getDestino())
            .build();
    }

    public static BulkDataProcessResultDto success(SolicitudDto solicitud) {
        return BulkDataProcessResultDto
            .builder()
            .success(true)
            .solicitudId(solicitud.getId())
            .cvu(solicitud.getSolicitante().getCvu())
            .destino(solicitud.getEstado())
            .build();
    }

    /**
     * Crea un objecto de tipo BulkResponseDto a partir de una Lista de objectos
     * BulkDataProcessResultDto resultado de la ejecución en bulk de varias acciones
     *
     * @param result
     * @return BulkResponseDto
     */
    public static BulkResponseDto response(List<BulkDataProcessResultDto> result) {
        return BulkResponseDto.builder().processed(result.size()).processedElements(result).build();
    }

    /**
     * Crea un objecto de tipo BulkDataProcessResultDto a partir de un objecto
     * TransicionContextDto para operaciones que no han resultado exitosas, mapeando
     * la lista de reglas que no se cumplieron
     *
     * @param contexto
     * @param throwable
     * @return BulkDataProcessResultDto
     */
    public static BulkDataProcessResultDto failure(TransicionContextDto contexto, Throwable throwable) {
        List<RuleDto> errores = new ArrayList<>();
        if (throwable instanceof RulesException) {
            RulesException rulesException = (RulesException) throwable;
            errores = rulesException.getErrores();
        }
        return BulkDataProcessResultDto
            .builder()
            .success(false)
            .solicitudId(contexto.getSolicitudId())
            .destino(contexto.getTransicion().getDestino())
            .errors(errores)
            .build();
    }

    public static BulkDataProcessResultDto failure(SolicitudDto solicitud, Throwable throwable) {
        List<RuleDto> errores = new ArrayList<>();
        if (throwable instanceof RulesException) {
            RulesException rulesException = (RulesException) throwable;
            errores = rulesException.getErrores();
        }
        if (throwable instanceof FeignException.NotFound) {
            errores = RuleExceptionFactory.cvuNotFound().getErrores();
        }
        if (throwable instanceof NoFallbackAvailableException) {
            errores = RuleExceptionFactory.cvuNotFound().getErrores();
        }
        return BulkDataProcessResultDto
            .builder()
            .success(false)
            .cvu(solicitud.getSolicitante().getCvu())
            .solicitudId(solicitud.getId())
            .destino(solicitud.getEstado())
            .errors(errores)
            .build();
    }
}
