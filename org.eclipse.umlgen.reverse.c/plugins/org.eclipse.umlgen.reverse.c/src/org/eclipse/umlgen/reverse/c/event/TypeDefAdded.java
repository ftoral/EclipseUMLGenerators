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

/**
 * Event related to addition of a type definition.
 *
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 */
public class TypeDefAdded extends TypeDefEvent {

	/**
	 * Gets the right builder
	 *
	 * @return the builder for this event
	 */
	public static Builder<TypeDefAdded> builder() {
		return new Builder<TypeDefAdded>() {
			private TypeDefAdded event = new TypeDefAdded();

			/**
			 * @see org.eclipse.umlgen.reverse.c.TypeDefBuilder#getEvent()
			 */
			@Override
			protected TypeDefAdded getEvent() {
				return event;
			}
		};
	}
}
