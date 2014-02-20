package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestContinue extends AbstractTest {

	@Test
	public void continueInNestedForAndIf() {
		testCFile("continueStatement/continueInForAndIfNested.c", false);
	}
}
