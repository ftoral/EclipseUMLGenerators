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
