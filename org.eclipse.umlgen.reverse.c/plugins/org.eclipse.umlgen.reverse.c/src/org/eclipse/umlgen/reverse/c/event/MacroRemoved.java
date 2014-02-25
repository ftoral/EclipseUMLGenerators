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

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.umlgen.reverse.c.resource.ModelManager;
import org.eclipse.umlgen.reverse.c.util.DiagramUtil;
import org.eclipse.umlgen.reverse.c.util.ModelUtil;
import org.eclipse.umlgen.reverse.c.util.ModelUtil.EventType;

/**
 * Event related to a deletion of a macro.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @since 4.0.0
 */
public class MacroRemoved extends MacroEvent {
	/**
	 * @see org.eclipse.umlgen.reverse.c.CModelChangedEvent#notifyChanges()
	 */
	@Override
	public void notifyChanges(ModelManager manager) {
		Classifier matchingClassifier = ModelUtil.findClassifierInPackage(
				manager.getSourcePackage(), getUnitName());
		Property attribute = matchingClassifier.getAttribute(getCurrentName(),
				null);

		if (attribute != null) {
			if (ModelUtil.isRemovable(attribute)) {
				DiagramUtil.removeGraphicalRepresentation(attribute, manager);
				attribute.destroy();
			} else {
				ModelUtil.setVisibility(attribute, getTranslationUnit(),
						EventType.REMOVE);
			}
		}
	}

	/**
	 * Gets the right builder
	 * 
	 * @return the builder for this event
	 */
	public static Builder<MacroRemoved> builder() {
		return new Builder<MacroRemoved>() {
			private MacroRemoved event = new MacroRemoved();

			/**
			 * @see org.eclipse.umlgen.reverse.c.MacroBuilder#getEvent()
			 */
			@Override
			protected MacroRemoved getEvent() {
				return event;
			}
		};
	}
}
