/***********************************************************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Christophe Le Camus (CS) Mikael Barbero (Obeo) Sebastien GABEL (CS)
 * 
 **********************************************************************************************************************/
package org.eclipse.umlgen.reverse.c.listener;

import org.eclipse.core.resources.IResource;
import org.eclipse.umlgen.reverse.c.StructuralBuilder;
import org.eclipse.umlgen.reverse.c.event.CModelChangedEvent;
import org.eclipse.umlgen.reverse.c.resource.ModelManager;

public class UMLModelChangeListener implements ICModelChangeListener {
	/** The associated model manager **/
	private ModelManager manager;

	/**
	 * Constructor
	 */
	public UMLModelChangeListener() {
		// do nothing
	}

	/**
	 * Constructor invoked since {@link StructuralBuilder} class.
	 * 
	 * @param rsc
	 *            A workspace resource
	 */
	public UMLModelChangeListener(IResource rsc) {
		manager = getModelManager(rsc);
	}

	/**
	 * Disposes the model manager
	 */
	public void dispose() {
		if (manager != null) {
			manager.dispose();
			manager = null;
		}
	}

	/**
	 * Gets the model manager according to the resource sent in parameter
	 * 
	 * @param rsc
	 * @return
	 */
	private ModelManager getModelManager(IResource rsc) {
		if (manager == null) {
			manager = createModelManager(rsc);
		} else if (!manager.getProject().equals(rsc.getProject())) {
			manager.dispose();
			manager = createModelManager(rsc);
		}
		return manager;
	}

	/**
	 * Creates a new model manager based on a resource
	 * 
	 * @param rsc
	 *            The workspace resource
	 * @return The newly resource manager
	 */
	private ModelManager createModelManager(IResource rsc) {
		return new ModelManager(rsc);
	}

	/**
	 * Gets the model manager
	 * 
	 * @return the instantiated model manager
	 */
	public ModelManager getModelManager() {
		return manager;
	}

	/**
	 * @see org.eclipse.umlgen.reverse.c.listener.ICModelChangeListener#notifyChanges(org.eclipse.umlgen.reverse.c.event.CModelChangedEvent,
	 *      boolean)
	 */
	public void notifyChanges(final CModelChangedEvent event, boolean needSave) {
		if (event != null) {
			ModelManager manager = getModelManager(event.getTranslationUnit()
					.getResource());
			event.notifyChanges(manager);
			if (needSave) {
				manager.saveModels();
			}
		}
	}

}
