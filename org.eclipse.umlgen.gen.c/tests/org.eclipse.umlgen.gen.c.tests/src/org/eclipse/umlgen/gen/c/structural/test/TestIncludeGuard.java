package org.eclipse.umlgen.gen.c.structural.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.structural.test.util.AbstractTest;

public class TestIncludeGuard extends AbstractTest {

	@Test(expected = AssertionError.class)
	public void testIncludeGuard1() {
		testStructuralHFile("includeGuard", "aFile", "includeGuard1");
	}

	@Test(expected = AssertionError.class)
	public void testIncludeGuard2() {
		testStructuralHFile("includeGuard", "aFile", "includeGuard2");
	}

	@Test(expected = AssertionError.class)
	public void testIncludeGuard3() {
		testStructuralHFile("includeGuard", "aFile", "includeGuard3");
	}

	@Test
	public void testIncludeGuard4() {
		testStructuralHFile("includeGuard", "aFile", "includeGuard4");
	}

}
