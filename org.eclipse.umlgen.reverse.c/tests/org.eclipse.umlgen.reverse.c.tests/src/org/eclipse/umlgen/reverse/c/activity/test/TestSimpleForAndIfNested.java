package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestSimpleForAndIfNested extends AbstractTest {

	@Test
	public void simpleForAndIfNested1() {
		testCFile("forAndIfNested/simpleForAndIfNested1.c", false);
	}

	@Test
	public void simpleForAndIfNested2() {
		testCFile("forAndIfNested/simpleForAndIfNested2.c", false);
	}
}
