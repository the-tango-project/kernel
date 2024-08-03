package org.apeiron.kernel.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");

    //FIXME: Check if we must to remove the next constants
    public static final String ERR_GENERAL = "error.general";
    public static final String ERR_BAD_REQUEST = "error.badRequest";
    public static final String ERR_SERVICE_UNAVAILABLE = "error.serviceUnavailable";
    public static final String ERR_NOT_FOUND = "error.notFound";
    public static final String ERR_INVALID_ARGUMENT = "error.invalidArgument";
    public static final String ERR_FORMALIZACION = "error.formalizacion";
    public static final String ERR_GENERAR_PDF = "error.generarPdf";
    public static final String ERR_EXPEDIENTE = "error.expediente";
    public static final String ERR_INSACULACION = "error.insaculacion";
    public static final String ERR_CONFLICT = "error.conflict";
    public static final String ERR_LIBERACION_BECAS = "error.liberacionBecas";


    private ErrorConstants() {}
}
