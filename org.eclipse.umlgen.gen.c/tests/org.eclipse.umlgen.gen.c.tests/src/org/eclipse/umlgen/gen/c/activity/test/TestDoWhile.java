package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestDoWhile extends AbstractTest {

	@Test
	public void testEmpty() {
		testUmlActivityFile("doWhileLoop/empty.uml");
	}

	@Test
	public void testIfAsFirst() {
		testUmlActivityFile("doWhileLoop/ifAsFirst.uml");
	}

	@Test
	public void testStandard() {
		testUmlActivityFile("doWhileLoop/standard.uml");
	}

	@Test
	public void testNested() {
		testUmlActivityFile("doWhileLoop/nested.uml");
	}
}
