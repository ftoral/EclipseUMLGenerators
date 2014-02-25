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
package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestComments extends AbstractTest {
	@Test
	public void testComplex() {
		testUmlActivityFile("comments/complex.uml");
	}

	@Test
	public void testIf1() {
		testUmlActivityFile("comments/if1.uml");
	}

	@Test
	public void testIf2() {
		testUmlActivityFile("comments/if2.uml");
	}
}
