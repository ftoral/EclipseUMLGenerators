package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestSpecialChars extends AbstractTest {
	@Test
	public void testSpecialChars() {
		testCFile("specialChars/specialCharsAmpersand.c", false);
	}
}