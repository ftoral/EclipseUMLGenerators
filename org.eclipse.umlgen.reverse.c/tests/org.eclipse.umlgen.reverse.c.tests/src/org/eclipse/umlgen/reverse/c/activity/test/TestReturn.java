package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestReturn extends AbstractTest {

	@Test
	public void returnInNestedForAndIf() {
		testCFile("returnStatement/returnInNestedForAndIf.c", false);
	}

	@Test
	public void returnInNestedForAndIf2() {
		testCFile("returnStatement/returnInNestedForAndIf2.c", false);
	}

	@Test
	public void returnInSwitch() {
		testCFile("returnStatement/returnInSwitch.c", false);
	}

	@Test
	public void returnInIf() {
		testCFile("returnStatement/returnInIf.c", false);
	}

	@Test
	public void returnInElse() {
		testCFile("returnStatement/returnInElse.c", false);
	}
}
