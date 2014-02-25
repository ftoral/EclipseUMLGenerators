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

public abstract class FunctionDefinitionEvent extends FunctionDeclarationEvent {

	/** The body of the function */
	private String body;

	public String getBody() {
		return body;
	}

	protected void setBody(String impl) {
		body = impl;
	}

	public abstract static class Builder<T extends FunctionDefinitionEvent>
			extends FunctionDeclarationEvent.Builder<T> {
		public Builder<T> setBody(String body) {
			getEvent().setBody(body);
			return this;
		}
	}

}
