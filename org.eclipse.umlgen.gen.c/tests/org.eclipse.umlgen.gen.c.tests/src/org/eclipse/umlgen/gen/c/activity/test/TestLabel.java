package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestLabel extends AbstractTest {

	@Test
	public void testBeforeIf() {
		testUmlActivityFile("label/beforeIf.uml");
	}

	@Test
	public void testSimple() {
		testUmlActivityFile("label/simple.uml");
	}
}
