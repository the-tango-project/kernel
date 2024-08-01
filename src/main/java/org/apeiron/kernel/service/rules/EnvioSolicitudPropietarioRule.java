package org.apeiron.kernel.service.rules;

import org.apeiron.kernel.commons.annotations.ApeironRule;
import org.apeiron.kernel.commons.annotations.Condition;
import org.apeiron.kernel.commons.annotations.Tags;
import org.apeiron.kernel.configuration.Constants.RuleTag;
import org.apeiron.kernel.security.SecurityUtils;
import org.apeiron.kernel.service.dto.ContextDto;
import org.apeiron.kernel.service.validator.IRule;
import reactor.core.publisher.Mono;

@ApeironRule(nombre = "Envio de solicitud por propietario")
@Condition("Un solicitante es el único que puede enviar su solicitud")
@Tags({ RuleTag.Must.PROPIETARIO_SOLICITUD })
public class EnvioSolicitudPropietarioRule implements IRule {

    @Override
    public Mono<Boolean> validate(ContextDto context) {
        return SecurityUtils
            .getCurrentUserLogin()
            .switchIfEmpty(Mono.just("anonymous"))
            .flatMap(usuario -> {
                return Mono.just(context.getSolicitud().getUsuario().equals(usuario));
            });
    }
}
