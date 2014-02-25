/*******************************************************************************
 * Copyright (c) 2010 Obeo and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *      Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.gen.c.structural.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.structural.test.util.AbstractTest;

public class TestIncludes extends AbstractTest {

	@Test
	public void includesExtH() {
		testStructuralHFile("includes", "file", "includesExtH");
	}

	@Test
	public void includesExtC() {
		testStructuralCFile("includes", "file", "includesExtC");
	}

	@Test
	public void sameNameExternalC() {
		testStructuralCFile("includes", "sameNameExternal", "sameNameExternalC");
	}

	@Test
	public void sameNameInternalC() {
		testStructuralCFile("includes", "sameNameInternal", "sameNameInternalC");
	}
}
