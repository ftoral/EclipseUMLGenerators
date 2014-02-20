package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestSimple extends AbstractTest {
	@Test
	public void emptyActivity() {
		testCFile("simple/testEmptyActivity.c", false);
	}

	@Test
	public void noControlStucture() {
		testCFile("simple/testNoControlStructure.c", false);
	}

	@Test
	public void singleIf() {
		testCFile("simple/testSingleIf.c", false);
	}

	@Test
	public void ifElse() {
		testCFile("simple/testIfElse.c", false);
	}
}
