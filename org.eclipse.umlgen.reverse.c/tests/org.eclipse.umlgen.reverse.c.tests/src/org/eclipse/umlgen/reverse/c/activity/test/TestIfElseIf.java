package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestIfElseIf extends AbstractTest {
	@Test
	public void ifElseIf1() {
		testCFile("ifElseIf/testIfElseIf1.c", false);
	}

	@Test
	public void ifElseIf2() {
		testCFile("ifElseIf/testIfElseIf2.c", false);
	}

	@Test
	public void ifElseIf3() {
		testCFile("ifElseIf/testIfElseIf3.c", false);
	}

	@Test
	public void ifElseIf4() {
		testCFile("ifElseIf/testIfElseIf4.c", false);
	}
}
