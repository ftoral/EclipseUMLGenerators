/*******************************************************************************
 * Copyright (c) 2011 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sebastien Gabel (CS) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.ui.properties.sections;

import org.eclipse.umlgen.reverse.c.AnnotationConstants;
import org.eclipse.umlgen.reverse.c.ui.internal.bundle.Messages;

/**
 * Section allowing to specify a conditional inclusion (#ifndef).<br>
 * This section is available from a {@link org.eclipse.uml2.uml.Class} or a
 * {@link org.eclipse.uml2.uml.Interface}.<br>
 * 
 * Creation : 15 february 2010<br>
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
// FIXME MIGRATION reference to org.topcased.tabbedproperties
public class IfNDefSection extends AbstractModuleSection {

	/**
	 * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getLabelText()
	 */
	@Override
	protected String getLabelText() {
		return Messages.getString("IfNDefSection.label"); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.umlgen.reverse.c.ui.properties.sections.AbstractModuleSection#getKeyDetailsEntry()
	 */
	@Override
	protected String getKeyDetailsEntry() {
		return AnnotationConstants.IFNDEF_CONDITION;
	}

}
