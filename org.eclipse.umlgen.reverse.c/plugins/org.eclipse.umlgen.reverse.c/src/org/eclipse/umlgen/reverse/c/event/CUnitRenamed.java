/*******************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *     Christophe Le Camus (CS) - initial API and implementation 
 *     Sebastien Gabel (CS) - evolutions
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.umlgen.reverse.c.resource.ModelManager;

public class CUnitRenamed extends CUnitEvent {

	/**
	 * @see org.eclipse.umlgen.reverse.c.event.CModelChangedEvent#notifyChanges(org.eclipse.umlgen.reverse.c.resource.ModelManager)
	 */
	@Override
	public void notifyChanges(ModelManager manager) {
		// TODO : to be implemented
	}

	/**
	 * Gets the right builder
	 * 
	 * @return the builder for this event
	 */
	public static Builder<CUnitRenamed> builder() {
		return new Builder<CUnitRenamed>() {
			private CUnitRenamed event = new CUnitRenamed();

			/**
			 * @see org.eclipse.umlgen.reverse.c.event.CUnitEvent.Builder#getEvent()
			 */
			@Override
			protected CUnitRenamed getEvent() {
				return event;
			}
		};
	}
}
