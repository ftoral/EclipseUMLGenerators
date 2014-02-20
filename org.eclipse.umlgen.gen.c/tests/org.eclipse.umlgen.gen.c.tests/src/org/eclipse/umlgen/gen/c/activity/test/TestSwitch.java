package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestSwitch extends AbstractTest {

	@Test
	public void testBigCaseClause() {
		testUmlActivityFile("switchConditional/bigCaseClause.uml");
	}

	@Test
	public void testCaseBreak() {
		testUmlActivityFile("switchConditional/caseBreak.uml");
	}

	@Test
	public void testFallthrough() {
		testUmlActivityFile("switchConditional/fallthrough.uml");
	}

	@Test
	public void testNoDefault() {
		testUmlActivityFile("switchConditional/noDefault.uml");
	}

	@Test
	public void testSpecial() {
		testUmlActivityFile("switchConditional/special.uml");
	}

	@Test
	public void testStandard() {
		testUmlActivityFile("switchConditional/standard.uml");
	}

	@Test
	public void testWithBreakAndContinue() {
		testUmlActivityFile("switchConditional/withBreakAndContinue.uml");
	}

	@Test
	public void testWithoutBreak() {
		testUmlActivityFile("switchConditional/withoutBreak.uml");
	}

}
