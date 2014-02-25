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

import java.io.IOException;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.structural.test.util.AbstractTest;

public class TestCUnit extends AbstractTest {

	@Test
	public void cEmpty() {
		testStructuralCFile("empty", "empty", "emptyC");
	}

	@Test
	public void hEmpty() {
		testStructuralHFile("empty", "empty", "emptyH");
	}

	@Test
	public void cAndHEmpty() {
		testStructuralFiles("empty", "empty", "emptyHandC");
	}
}
