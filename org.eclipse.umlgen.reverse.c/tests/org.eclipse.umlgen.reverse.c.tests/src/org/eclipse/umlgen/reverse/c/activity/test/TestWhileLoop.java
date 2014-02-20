package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestWhileLoop extends AbstractTest {

	@Test
	public void empty() {
		testCFile("whileLoop/empty.c", false);
	}

	@Test
	public void standard() {
		testCFile("whileLoop/standard.c", false);
	}

	@Test
	public void nested() {
		testCFile("whileLoop/nested.c", false);
	}

	@Test
	public void ifAsFirst() {
		testCFile("whileLoop/ifAsFirst.c", false);
	}

	@Test
	public void withoutBody() {
		testCFile("whileLoop/withoutBody.c", false);
	}
}
