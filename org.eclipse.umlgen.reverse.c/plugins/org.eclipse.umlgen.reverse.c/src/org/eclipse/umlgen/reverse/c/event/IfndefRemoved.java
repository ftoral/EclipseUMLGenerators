/***********************************************************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sebastien GABEL (CS) - initial API and implementation
 * 
 **********************************************************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.umlgen.reverse.c.AnnotationConstants;
import org.eclipse.umlgen.reverse.c.resource.ModelManager;
import org.eclipse.umlgen.reverse.c.util.ModelUtil;

/**
 * Event related to a deletion of an Ifndef declaration.
 * 
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @since 4.0.0
 */
public class IfndefRemoved extends IfndefEvent {

	/**
	 * @see org.eclipse.umlgen.reverse.c.CModelChangedEvent#notifyChanges(org.eclipse.umlgen.reverse.c.resource.ModelManager)
	 */
	@Override
	public void notifyChanges(ModelManager manager) {
		Classifier matchingClassifier = ModelUtil.findClassifierInPackage(
				manager.getSourcePackage(), getUnitName());
		EAnnotation annot = matchingClassifier
				.getEAnnotation(AnnotationConstants.REVERSE_PROCESS);
		if (annot != null) {
			annot.getDetails().removeKey(AnnotationConstants.IFNDEF_CONDITION);
			if (annot.getDetails().isEmpty()) {
				matchingClassifier.getEAnnotations().remove(annot);
			}
		}
	}

	/**
	 * Gets the right builder
	 * 
	 * @return the builder for this event
	 */
	public static Builder<IfndefRemoved> builder() {
		return new Builder<IfndefRemoved>() {
			private IfndefRemoved event = new IfndefRemoved();

			/**
			 * @see org.eclipse.umlgen.reverse.c.IfndefBuilder#getEvent()
			 */
			@Override
			protected IfndefRemoved getEvent() {
				return event;
			}
		};
	}
}
