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

public class TestForAndIfNested extends AbstractTest {

	@Test
	public void testSimpleForAndIfNested1() {
		testUmlActivityFile("forAndIfNested/simpleForAndIfNested1.uml");
	}

	@Test
	public void testSimpleForAndIfNested2() {
		testUmlActivityFile("forAndIfNested/simpleForAndIfNested2.uml");
	}
}
