package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiagramDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private double x;

    private double y;

    private String sourceId;

    private String targetId;
}
