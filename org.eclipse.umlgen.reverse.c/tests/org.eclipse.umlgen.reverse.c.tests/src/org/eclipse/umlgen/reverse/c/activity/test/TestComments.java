package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestComments extends AbstractTest {
	@Test
	public void testComplex() {
		testCFile("comments/complex.c", false);
	}

	@Test
	public void testIf1() {
		testCFile("comments/if1.c", false);
	}

	@Test
	public void testIf2() {
		testCFile("comments/if2.c", false);
	}
}
