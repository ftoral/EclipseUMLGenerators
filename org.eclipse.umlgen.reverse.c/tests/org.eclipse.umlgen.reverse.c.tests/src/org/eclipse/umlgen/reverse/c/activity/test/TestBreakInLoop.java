package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestBreakInLoop extends AbstractTest {

	@Test
	public void breakInNestedFor() {
		testCFile("breakInLoop/breakInNestedFor.c", false);
	}

	@Test
	public void breakInNestedForAndIf() {
		testCFile("breakInLoop/breakInNestedForAndIf.c", false);
	}

	@Test
	public void breakInNestedForAndIf2() {
		testCFile("breakInLoop/breakInNestedForAndIf2.c", false);
	}
}
