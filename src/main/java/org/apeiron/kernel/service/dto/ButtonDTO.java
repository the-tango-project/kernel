package org.apeiron.kernel.service.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;

@Data
@ToString
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ButtonDTO extends AbstractExtensibleDTO {

    private String icon;

    private String nombre;

    private String destino;

    private String tooltip;

    private List<RolAutoridad> roles = new ArrayList<>();

    private String expresion;
}
