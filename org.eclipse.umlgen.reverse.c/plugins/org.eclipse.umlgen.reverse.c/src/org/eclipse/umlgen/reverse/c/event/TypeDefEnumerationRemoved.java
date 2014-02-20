/***********************************************************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 	Mickael BARBERO ( OBEO) - initial API and implementation
 * 					Christophe LE CAMUS (CS) - Major evolution
 * 					Sebastien GABEl (CS) - Refactoring
 * 
 **********************************************************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.umlgen.reverse.c.resource.ModelManager;
import org.eclipse.umlgen.reverse.c.util.DiagramUtil;
import org.eclipse.umlgen.reverse.c.util.ModelUtil;
import org.eclipse.umlgen.reverse.c.util.ModelUtil.EventType;

public class TypeDefEnumerationRemoved extends TypeDefEnumerationEvent {
	/**
	 * @see org.eclipse.umlgen.reverse.c.CModelChangedEvent#notifyChanges(org.eclipse.umlgen.reverse.c.resource.ModelManager)
	 */
	@Override
	public void notifyChanges(ModelManager manager) {
		Classifier matchingClassifier = ModelUtil.findClassifierInPackage(
				manager.getSourcePackage(), getUnitName());
		Enumeration myEnumeration = ModelUtil.findEnumerationInClassifier(
				matchingClassifier, getCurrentName());
		if (myEnumeration != null) {
			if (ModelUtil.isRemovable(myEnumeration)) {
				DiagramUtil.removeGraphicalRepresentation(myEnumeration,
						manager);
				myEnumeration.destroy();
			} else {
				ModelUtil.setVisibility(myEnumeration, getTranslationUnit(),
						EventType.REMOVE);
			}
		}
	}

	/**
	 * Gets the right builder
	 * 
	 * @return the builder for this event
	 */
	public static Builder<TypeDefEnumerationRemoved> builder() {
		return new Builder<TypeDefEnumerationRemoved>() {
			private TypeDefEnumerationRemoved event = new TypeDefEnumerationRemoved();

			@Override
			protected TypeDefEnumerationRemoved getEvent() {
				return event;
			}
		};
	}
}
