package org.apeiron.kernel.service.util.layout;

import static java.util.Objects.nonNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Column {

    private String name;
    private String desc;
    private String validationReference;

    public boolean hasValidation() {
        return nonNull(validationReference);
    }
}
