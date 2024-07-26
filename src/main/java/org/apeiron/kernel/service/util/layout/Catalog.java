package org.apeiron.kernel.service.util.layout;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Catalog {

    List<CatalogItem> items;
}
