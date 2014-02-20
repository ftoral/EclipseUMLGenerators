package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestDoWhileLoop extends AbstractTest {

	@Test
	public void empty() {
		testCFile("doWhileLoop/empty.c", false);
	}

	@Test
	public void standard() {
		testCFile("doWhileLoop/standard.c", false);
	}

	@Test
	public void nested() {
		testCFile("doWhileLoop/nested.c", false);
	}

	@Test
	public void ifAsFirst() {
		testCFile("doWhileLoop/ifAsFirst.c", false);
	}
}
