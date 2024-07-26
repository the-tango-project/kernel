package org.apeiron.kernel.domain.enumeration;

/**
 * The TipoComentario enumeration.
 */
public enum TipoReglaFiltro {
    TEXT_IS_EMPTY,
    TEXT_IS_NOT_EMPTY,
    TEXT_CONTAINS,
    TEXT_NOT_CONTAINS,
    TEXT_BEGIN_WITH,
    TEXT_FINISH_WITH,
    TEXT_EQUAL_TO,
    TEXT_IS_IN_LIST,
    TEXT_IS_IN_COMMA_SEPARATED_VALUES,
    TEXT_IS_NOT_IN_LIST,
}
