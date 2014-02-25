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
 * Abstract representation of an event related to an inclusion.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @since 4.0.0
 */
public abstract class IncludeEvent extends AbstractNamedEvent {

	private boolean isStandard;

	public boolean getIsStandard() {
		return isStandard;
	}

	protected void setStandard(boolean isStandard) {
		this.isStandard = isStandard;
	}

	public static abstract class Builder<T extends IncludeEvent> extends
			AbstractNamedEvent.Builder<T> {

		public Builder<T> setStandard(boolean standard) {
			getEvent().setStandard(standard);
			return this;
		}
	}
}
