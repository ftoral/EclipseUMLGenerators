package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestSpecialChars extends AbstractTest {

	@Test
	public void testSpecialCharsAmpersand() {
		testUmlActivityFile("specialChars/specialCharsAmpersand.uml");
	}

}
