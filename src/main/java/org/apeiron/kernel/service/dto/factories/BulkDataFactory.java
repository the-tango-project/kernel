package org.apeiron.kernel.service.dto.factories;

import feign.FeignException;
import java.util.ArrayList;
import java.util.List;
import org.apeiron.kernel.service.dto.BulkDataProcessResultDTO;
import org.apeiron.kernel.service.dto.BulkResponseDTO;
import org.apeiron.kernel.service.dto.RuleDTO;
import org.apeiron.kernel.service.dto.SolicitudDTO;
import org.apeiron.kernel.service.dto.TransicionContextDTO;
import org.apeiron.kernel.service.exception.RuleExceptionFactory;
import org.apeiron.kernel.service.exception.RulesException;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

public class BulkDataFactory {

    private BulkDataFactory() {}

    /**
     * Crea un objecto de tipo BulkDataProcessResultDTO a partir de un objecto
     * TransicionContextDTO para operaciones que han resultado exitosas
     *
     * @param contexto
     * @return BulkDataProcessResultDTO
     */
    public static BulkDataProcessResultDTO success(TransicionContextDTO contexto) {
        return BulkDataProcessResultDTO
            .builder()
            .success(true)
            .solicitudId(contexto.getSolicitudId())
            .destino(contexto.getTransicion().getDestino())
            .build();
    }

    public static BulkDataProcessResultDTO success(SolicitudDTO solicitud) {
        return BulkDataProcessResultDTO
            .builder()
            .success(true)
            .solicitudId(solicitud.getId())
            .cvu(solicitud.getSolicitante().getCvu())
            .destino(solicitud.getEstado())
            .build();
    }

    /**
     * Crea un objecto de tipo BulkResponseDTO a partir de una Lista de objectos
     * BulkDataProcessResultDTO resultado de la ejecución en bulk de varias acciones
     *
     * @param result
     * @return BulkResponseDTO
     */
    public static BulkResponseDTO response(List<BulkDataProcessResultDTO> result) {
        return BulkResponseDTO.builder().processed(result.size()).processedElements(result).build();
    }

    /**
     * Crea un objecto de tipo BulkDataProcessResultDTO a partir de un objecto
     * TransicionContextDTO para operaciones que no han resultado exitosas, mapeando
     * la lista de reglas que no se cumplieron
     *
     * @param contexto
     * @param throwable
     * @return BulkDataProcessResultDTO
     */
    public static BulkDataProcessResultDTO failure(TransicionContextDTO contexto, Throwable throwable) {
        List<RuleDTO> errores = new ArrayList<>();
        if (throwable instanceof RulesException) {
            RulesException rulesException = (RulesException) throwable;
            errores = rulesException.getErrores();
        }
        return BulkDataProcessResultDTO
            .builder()
            .success(false)
            .solicitudId(contexto.getSolicitudId())
            .destino(contexto.getTransicion().getDestino())
            .errors(errores)
            .build();
    }

    public static BulkDataProcessResultDTO failure(SolicitudDTO solicitud, Throwable throwable) {
        List<RuleDTO> errores = new ArrayList<>();
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
        return BulkDataProcessResultDTO
            .builder()
            .success(false)
            .cvu(solicitud.getSolicitante().getCvu())
            .solicitudId(solicitud.getId())
            .destino(solicitud.getEstado())
            .errors(errores)
            .build();
    }
}
