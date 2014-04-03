/*******************************************************************************
 * Copyright (c) 2010, 2014 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	   Christophe Le Camus (CS) - initial API and implementation 
 *     Sebastien Gabel (CS) - evolutions
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.umlgen.reverse.c.resource.ModelManager;
import org.eclipse.umlgen.reverse.c.util.DiagramUtil;
import org.eclipse.umlgen.reverse.c.util.ModelUtil;
import org.eclipse.umlgen.reverse.c.util.ModelUtil.EventType;

public class TypeDefRemoved extends TypeDefEvent {
	/**
	 * @see org.eclipse.umlgen.reverse.c.CModelChangedEvent#notifyChanges(org.eclipse.umlgen.reverse.c.resource.ModelManager)
	 */
	@Override
	public void notifyChanges(ModelManager manager) {
		Classifier matchingClassifier = ModelUtil.findClassifierInPackage(
				manager.getSourcePackage(), getUnitName());
		Classifier localType = ModelUtil.findDataTypeInClassifier(
				matchingClassifier, getCurrentName());
		if (localType != null) {
			if (ModelUtil.isRemovable(localType)) {
				DiagramUtil.removeGraphicalRepresentation(localType, manager);
				localType.destroy();
			} else {
				ModelUtil.setVisibility(localType, getTranslationUnit(),
						EventType.REMOVE);
			}
		}
	}

	/**
	 * Gets the right builder
	 * 
	 * @return the builder for this event
	 */
	public static Builder<TypeDefRemoved> builder() {
		return new Builder<TypeDefRemoved>() {
			private TypeDefRemoved event = new TypeDefRemoved();

			/**
			 * @see org.eclipse.umlgen.reverse.c.TypeDefBuilder#getEvent()
			 */
			@Override
			protected TypeDefRemoved getEvent() {
				return event;
			}
		};
	}
}
