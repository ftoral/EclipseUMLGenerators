package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestContinue extends AbstractTest {

	@Test
	public void testContinueInForAndIfNested() {
		testUmlActivityFile("continueStatement/continueInForAndIfNested.uml");
	}

}
