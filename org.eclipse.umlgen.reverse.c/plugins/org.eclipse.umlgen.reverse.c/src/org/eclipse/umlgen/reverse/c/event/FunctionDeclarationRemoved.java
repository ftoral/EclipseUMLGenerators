/*******************************************************************************
 * Copyright (c) 2010, 2014 CS Systèmes d'Information (CS-SI).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Christophe Le Camus (CS-SI) - initial API and implementation
 *     Sebastien Gabel (CS-SI) - evolutions
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.umlgen.c.common.util.ModelManager;
import org.eclipse.umlgen.c.common.util.ModelUtil;
import org.eclipse.umlgen.reverse.c.util.DiagramUtil;

/**
 * Removes a {@link Operation} declaration from the model.
 */
public class FunctionDeclarationRemoved extends FunctionDeclarationEvent {
	/**
	 * @see org.eclipse.umlgen.reverse.c.CModelChangedEvent#notifyChanges(org.eclipse.umlgen.c.common.util.ModelManager)
	 */
	@Override
	public void notifyChanges(ModelManager manager) {
		Classifier matchingClassifier = ModelUtil.findClassifierInPackage(manager.getSourcePackage(),
				getUnitName());
		// initialize parameters in order to get the corresponding operations
		Operation operation = matchingClassifier.getOperation(getCurrentName(), null, null);
		if (operation != null) {
			if (ModelUtil.isRemovable(operation)) {
				operation.destroy();
				DiagramUtil.removeGraphicalRepresentation(operation, manager);
				for (Parameter parameter : operation.getOwnedParameters()) {
					Type parameterType = parameter.getType();
					if (ModelUtil.isNotReferencedAnymore(parameterType)) {
						ModelUtil.destroy((DataType)parameterType);
					}
				}
			}
		}
	}

	/**
	 * Gets the right builder
	 *
	 * @return the builder for this event
	 */
	public static Builder<FunctionDeclarationRemoved> builder() {
		return new Builder<FunctionDeclarationRemoved>() {
			private FunctionDeclarationRemoved event = new FunctionDeclarationRemoved();

			/**
			 * @see org.eclipse.umlgen.reverse.c.event.FunctionDeclarationEvent.Builder#getEvent()
			 */
			@Override
			protected FunctionDeclarationRemoved getEvent() {
				return event;
			}
		};
	}
}
