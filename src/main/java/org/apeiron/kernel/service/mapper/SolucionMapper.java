package org.apeiron.kernel.service.mapper;

import org.apeiron.kernel.domain.Solucion;
import org.apeiron.kernel.service.dto.SolucionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Solucion} and its DTO {@link SolucionDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {
        ComponenteMapper.class,
        MensajeMapper.class,
        CalendarioMapper.class,
        RuleMapper.class,
        ActionMapper.class,
        AutoridadMapper.class,
        ProcesoMapper.class,
        PermisoMapper.class,
        VistaResumenMapper.class,
        SolucionConfiguracionMapper.class,
    }
)
public interface SolucionMapper extends EntityMapper<SolucionDTO, Solucion> {}
