/***********************************************************************************************************************
 * Copyright (c) 2010,2011 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 	Mickael BARBERO (OBEO) - initial API and implementation
 * 					Christophe LE CAMUS (CS) - Major evolution
 * 					Sebastien GABEl (CS) - Refactoring
 * 
 **********************************************************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.cdt.core.dom.ast.IASTNode;

/**
 * Abstract representation of an event related to a type def enumeration.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @since 4.0.0
 */
public abstract class TypeDefEnumerationEvent extends EnumerationEvent {

	private String redefinedEnumerationName;

	private IASTNode source;

	public String getRedefinedEnumerationName() {
		return redefinedEnumerationName;
	}

	protected void setRedefinedEnumerationName(String redefinedEnumerationName) {
		this.redefinedEnumerationName = redefinedEnumerationName;
	}

	public IASTNode getSource() {
		return source;
	}

	public void setSource(IASTNode source) {
		this.source = source;
	}

	public static abstract class Builder<T extends TypeDefEnumerationEvent>
			extends EnumerationEvent.Builder<T> {

		public Builder<T> setRedefinedEnumeration(String name) {
			getEvent().setRedefinedEnumerationName(name);
			return this;
		}

		public Builder<T> setSource(IASTNode source) {
			getEvent().setSource(source);
			return this;
		}
	}
}
