/*******************************************************************************
 * Copyright (c) 2010, 2014 CS Systèmes d'Information (CS-SI).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sebastien Gabel (CS-SI) - initial API and implementation
 *     Christophe Le Camus (CS-SI)- evolutions
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.umlgen.c.common.util.ModelManager;

public abstract class CUnitEvent extends CModelChangedEvent {
	private IPath previousName;

	private IPath currentName;

	private ModelManager modelManager;

	public IPath getPreviousName() {
		return this.previousName;
	}

	public IPath getCurrentName() {
		return this.currentName;
	}

	public ModelManager getModelManager() {
		return this.modelManager;
	}

	protected void setModelMananager(ModelManager mngr) {
		this.modelManager = mngr;
	}

	protected void setCurrentName(String currentName) {
		this.currentName = new Path(currentName);
	}

	protected void setPreviousName(String previousName) {
		this.previousName = new Path(previousName);
	}

	public static abstract class Builder<T extends CUnitEvent> extends CModelChangedEvent.Builder<T> {

		@Override
		public Builder<T> translationUnit(ITranslationUnit tu) {
			getEvent().setTranslationUnit(tu);
			return this;
		}

		public Builder<T> functionName(String functionName) {
			getEvent().setCurrentName(functionName);
			return this;
		}

		public Builder<T> setModelMananager(ModelManager mngr) {
			getEvent().setModelMananager(mngr);
			return this;
		}

		public Builder<T> currentName(String currentName) {
			getEvent().setCurrentName(currentName);
			return this;
		}

		public Builder<T> previousName(String previousName) {
			getEvent().setPreviousName(previousName);
			return this;
		}

		/**
		 * @see org.eclipse.umlgen.reverse.c.event.CModelChangedEvent.Builder#getEvent()
		 */
		@Override
		protected abstract T getEvent();
	}
}
