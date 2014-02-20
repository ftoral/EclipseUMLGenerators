package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestFor extends AbstractTest {

	@Test
	public void testEmptyFor1() {
		testUmlActivityFile("forLoop/empty/emptyFor1.uml");
	}

	@Test
	public void testEmptyFor2() {
		testUmlActivityFile("forLoop/empty/emptyFor2.uml");
	}

	@Test
	public void testEmptyFor3() {
		testUmlActivityFile("forLoop/empty/emptyFor3.uml");
	}

	@Test
	public void testNestedFor1() {
		testUmlActivityFile("forLoop/nested/nestedFor1.uml");
	}

	@Test
	public void testNestedFor2() {
		testUmlActivityFile("forLoop/nested/nestedFor2.uml");
	}

	@Test
	public void testNestedFor3() {
		testUmlActivityFile("forLoop/nested/nestedFor3.uml");
	}

	@Test
	public void testNestedFor4() {
		testUmlActivityFile("forLoop/nested/nestedFor4.uml");
	}

	@Test
	public void testSimpleFor1() {
		testUmlActivityFile("forLoop/simple/simpleFor1.uml");
	}

	@Test
	public void testSimpleFor2() {
		testUmlActivityFile("forLoop/simple/simpleFor2.uml");
	}

}
