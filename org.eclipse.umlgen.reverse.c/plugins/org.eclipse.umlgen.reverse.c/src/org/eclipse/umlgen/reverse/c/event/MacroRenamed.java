/*******************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sebastien Gabel (CS) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.umlgen.reverse.c.resource.ModelManager;

/**
 * Event related to a renaming of a macro.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @since 4.0.0
 */
public class MacroRenamed extends MacroEvent {
	/**
	 * @see org.eclipse.umlgen.reverse.c.event.CModelChangedEvent#notifyChanges()
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
	public static Builder<MacroRenamed> builder() {
		return new Builder<MacroRenamed>() {
			private MacroRenamed event = new MacroRenamed();

			/**
			 * @see org.eclipse.umlgen.reverse.c.event.MacroEvent.Builder#getEvent()
			 */
			@Override
			protected MacroRenamed getEvent() {
				return event;
			}
		};
	}
}
