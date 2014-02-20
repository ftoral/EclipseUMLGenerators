package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestSimple extends AbstractTest {

	@Test
	public void testEmptyActivity() {
		testUmlActivityFile("simple/testEmptyActivity.uml");
	}

	@Test
	public void testIfElse() {
		testUmlActivityFile("simple/testIfElse.uml");
	}

	@Test
	public void testNoControlStructure() {
		testUmlActivityFile("simple/testNoControlStructure.uml");
	}

	@Test
	public void testSingleIf() {
		testUmlActivityFile("simple/testSingleIf.uml");
	}
}
