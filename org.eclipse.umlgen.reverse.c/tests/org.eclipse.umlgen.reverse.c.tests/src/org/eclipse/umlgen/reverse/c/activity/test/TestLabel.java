package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestLabel extends AbstractTest {
	@Test
	public void simple() {
		testCFile("label/simple.c", false);
	}

	@Test
	public void beforeIf() {
		testCFile("label/beforeIf.c", false);
	}
}
