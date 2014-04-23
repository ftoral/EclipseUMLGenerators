/*******************************************************************************
 * Copyright (c) 2010, 2014 CS Systèmes d'Information (CS-SI).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Christophe Le Camus (CS-SI) - initial API and implementation
 *     Sebastien Gabel (CS-SI) - evolutions
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.umlgen.c.common.util.ModelManager;

public class EnumerationRenamed extends EnumerationEvent {

	/**
	 * @see org.eclipse.umlgen.reverse.c.event.CModelChangedEvent#notifyChanges(org.eclipse.umlgen.c.common.util.ModelManager)
	 */
	@Override
	public void notifyChanges(ModelManager manager) {
	}

	/**
	 * Gets the right builder
	 *
	 * @return the builder for this event
	 */
	public static Builder<EnumerationRenamed> builder() {
		return new Builder<EnumerationRenamed>() {
			private EnumerationRenamed event = new EnumerationRenamed();

			/**
			 * @see org.eclipse.umlgen.reverse.c.event.EnumerationEvent.Builder#getEvent()
			 */
			@Override
			protected EnumerationRenamed getEvent() {
				return event;
			}
		};
	}
}
