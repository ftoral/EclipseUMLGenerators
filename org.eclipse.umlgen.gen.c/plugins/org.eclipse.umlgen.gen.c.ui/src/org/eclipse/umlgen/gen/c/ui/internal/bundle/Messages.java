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
package org.eclipse.umlgen.gen.c.ui.internal.bundle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Class for message internationalization.<br>
 * 
 * Created : 07 may 2010<br>
 * 
 * @author <a href="mailto:sebastien.gabel@sdfsdfs.fr">Sebastien Gabel</a>
 */
public final class Messages {
	private static final String BUNDLE_NAME = "org.eclipse.umlgen.gen.c.ui.internal.bundle.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
