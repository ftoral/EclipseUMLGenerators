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

import org.eclipse.cdt.core.dom.ast.IASTDeclaration;

/**
 * Abstract representation of an event related to a structure.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @since 4.0.0
 */
public abstract class StructEvent extends AbstractTypedEvent {

	private IASTDeclaration[] declarations;

	public IASTDeclaration[] getDeclarations() {
		return this.declarations;
	}

	protected void setDeclarations(IASTDeclaration[] declarations) {
		this.declarations = declarations;
	}

	public static abstract class Builder<T extends StructEvent> extends
			AbstractTypedEvent.Builder<T> {
		public Builder<T> setDeclarations(IASTDeclaration[] declarations) {
			getEvent().setDeclarations(declarations);
			return this;
		}
	}
}
