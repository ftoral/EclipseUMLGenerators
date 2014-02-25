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
package org.eclipse.umlgen.reverse.c.util;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.umlgen.reverse.c.internal.bundle.Messages;
import org.eclipse.umlgen.reverse.c.resource.ModelManager;

/**
 * Defines a set of static method for working on the diagram model.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @since 4.0.0
 */
public final class DiagramUtil {
	private DiagramUtil() {
		// avoid to be instantiated
	}

	/**
	 * Removes graphical representation(s) from the DI resource of a semantic
	 * model object.
	 * 
	 * @param object
	 *            The semantic object being removed from the UML model
	 * @param mngr
	 *            The Model Manager
	 */
	public static void removeGraphicalRepresentation(EObject object,
			ModelManager mngr) {
		// Editing domain is not use here, so it means that operation is
		// undoable.
		CompoundCommand cc = new CompoundCommand(
				Messages.getString("DiagramUtil.cmd.title")); //$NON-NLS-1$

		// FIXME MIGRATION reference to org.topcased.modeler
		// for (GraphElement elt :
		// Utils.getGraphElements(mngr.getDiagramsModel(), object))
		// {
		// cc.appendIfCanExecute(new GEFtoEMFCommandWrapper(new
		// DeleteGraphElementCommand(elt, true)));
		// }

		cc.execute();
	}
}
