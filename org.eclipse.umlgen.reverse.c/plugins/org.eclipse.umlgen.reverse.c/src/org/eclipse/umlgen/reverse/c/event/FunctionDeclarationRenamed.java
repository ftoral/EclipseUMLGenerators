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

public class FunctionDeclarationRenamed extends FunctionDeclarationEvent {
	/**
	 * @see org.eclipse.umlgen.reverse.c.event.CModelChangedEvent#notifyChanges(org.eclipse.umlgen.reverse.c.resource.ModelManager)
	 */
	@Override
	public void notifyChanges(ModelManager manager) {
	}

	/**
	 * Gets the right builder
	 * 
	 * @return the builder for this event
	 */
	public static Builder<FunctionDeclarationRenamed> builder() {
		return new Builder<FunctionDeclarationRenamed>() {
			private FunctionDeclarationRenamed event = new FunctionDeclarationRenamed();

			/**
			 * @see org.eclipse.umlgen.reverse.c.event.FunctionDeclarationEvent.Builder#getEvent()
			 */
			@Override
			protected FunctionDeclarationRenamed getEvent() {
				return event;
			}
		};
	}
}
