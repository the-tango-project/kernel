package org.apeiron.kernel.service.dto.factories;

import org.apeiron.kernel.domain.HistoricoSolucion;
import org.apeiron.kernel.domain.HistoricoSolucionId;
import org.apeiron.kernel.domain.Solucion;

public class HistoricoFactory {

    private HistoricoFactory() {}

    public static HistoricoSolucion toHistorico(Solucion solucion) {
        return new HistoricoSolucion(new HistoricoSolucionId(solucion.getId(), solucion.getVersion()), solucion);
    }

    public static HistoricoSolucionId createId(Solucion solucion) {
        return new HistoricoSolucionId(solucion.getId(), solucion.getVersion());
    }
}
