package org.apeiron.kernel.service.util.layout;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogItem {

    private String nameReference;
    private String[] data;
}
