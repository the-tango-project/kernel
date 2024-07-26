package org.apeiron.kernel.web.rest.errors;

import java.io.Serializable;

public class ErrorSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String title;

    private final String description;

    public ErrorSolicitud(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
