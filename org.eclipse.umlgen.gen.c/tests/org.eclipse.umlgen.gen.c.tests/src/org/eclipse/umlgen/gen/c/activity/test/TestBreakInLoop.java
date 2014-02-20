package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestBreakInLoop extends AbstractTest {
	@Test
	public void testBreakInNestedFor() {
		testUmlActivityFile("breakInLoop/breakInNestedFor.uml");
	}

	@Test
	public void testBreakInNestedForAndIf() {
		testUmlActivityFile("breakInLoop/breakInNestedForAndIf.uml");
	}

	@Test
	public void testBreakInNestedForAndIf2() {
		testUmlActivityFile("breakInLoop/breakInNestedForAndIf2.uml");
	}

}
