package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestReturn extends AbstractTest {

	@Test
	public void testReturnInElse() {
		testUmlActivityFile("returnStatement/returnInElse.uml");
	}

	@Test
	public void testReturnInIf() {
		testUmlActivityFile("returnStatement/returnInIf.uml");
	}

	@Test
	public void testReturnInNestedForAndIf() {
		testUmlActivityFile("returnStatement/returnInNestedForAndIf.uml");
	}

	@Test
	public void testReturnInNestedForAndIf2() {
		testUmlActivityFile("returnStatement/returnInNestedForAndIf2.uml");
	}

	@Test
	public void testReturnInSwitch() {
		testUmlActivityFile("returnStatement/returnInSwitch.uml");
	}

}
