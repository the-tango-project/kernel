package org.apeiron.kernel.service.transition;

import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.validator.IRule;

/**
 * Intefaz que define un elemento de transición en el proceso de validación y de
 * acciones en el flujo principal del Ápeiron. Los principales elementos de
 * transición conocidos son las reglas {@link IRule} y acciones {@link IAction}
 *
 * @see IAction
 * @see IRule
 */
public interface TransitionElement {}
