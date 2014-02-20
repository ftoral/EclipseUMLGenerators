package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestWhile extends AbstractTest {

	@Test
	public void testEmpty() {
		testUmlActivityFile("whileLoop/empty.uml");
	}

	@Test
	public void testIfAsFirst() {
		testUmlActivityFile("whileLoop/ifAsFirst.uml");
	}

	@Test
	public void testStandard() {
		testUmlActivityFile("whileLoop/standard.uml");
	}

	@Test
	public void testNested() {
		testUmlActivityFile("whileLoop/nested.uml");
	}

	@Test
	public void testWithoutBody() {
		testUmlActivityFile("whileLoop/withoutBody.uml");
	}
}
