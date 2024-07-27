package org.apeiron.kernel.service.rules;

import static org.apeiron.kernel.utils.ValidationUtils.isNotEmpy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apeiron.kernel.autoconfiguration.Constants.RuleTag;
import org.apeiron.kernel.commons.annotations.ApeironRule;
import org.apeiron.kernel.commons.annotations.Condition;
import org.apeiron.kernel.commons.annotations.Tags;
import org.apeiron.kernel.service.DocumentoService;
import org.apeiron.kernel.service.dto.ContextDTO;
import org.apeiron.kernel.service.dto.DocumentoConfiguracionDTO;
import org.apeiron.kernel.service.dto.DocumentoDTO;
import org.apeiron.kernel.service.validator.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@ApeironRule(nombre = "Documentos requeridos")
@Condition("La solicitud debe cumplir con todos los documentos que son requeridos")
@Tags({ RuleTag.DOCUMENTOS })
public class DocumentosRequeridosRule implements IRule {

    @Autowired
    private DocumentoService documentoService;

    @Override
    public Mono<Boolean> validate(ContextDTO context) {
        final List<DocumentoConfiguracionDTO> listDocumentos = context
            .getSolucion()
            .getComponentes()
            .stream()
            .flatMap(c -> c.getConfiguracion().getDocumentos().stream())
            .collect(Collectors.toList());

        return documentoService
            .findOne(context.getSolicitud().getId())
            .switchIfEmpty(Mono.just(new DocumentoDTO()))
            .map(DocumentoDTO::getProperties)
            .map(p ->
                listDocumentos
                    .stream()
                    .filter(f -> f.isRequerido())
                    .allMatch(m -> {
                        Object map = p.get(m.getNombrePropiedad());
                        if (map != null) {
                            String uri = (String) ((Map<?, ?>) map).get("uri");
                            return isNotEmpy(uri);
                        } else {
                            return false;
                        }
                    })
            );
    }
}
