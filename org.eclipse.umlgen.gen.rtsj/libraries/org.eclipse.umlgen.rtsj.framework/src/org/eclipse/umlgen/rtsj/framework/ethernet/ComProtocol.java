/*******************************************************************************
 * Copyright (c) 2012, 2014 CNES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Topcased contributors and others - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.rtsj.framework.ethernet;

import org.eclipse.umlgen.rtsj.framework.ArgsBuffer;

public interface ComProtocol {

	public void sendFrame (String dest, String ident, String service, ArgsBuffer params, int priority);
	
}
