package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestForAndIfNested extends AbstractTest {

	@Test
	public void testSimpleForAndIfNested1() {
		testUmlActivityFile("forAndIfNested/simpleForAndIfNested1.uml");
	}

	@Test
	public void testSimpleForAndIfNested2() {
		testUmlActivityFile("forAndIfNested/simpleForAndIfNested2.uml");
	}
}
