package org.eclipse.umlgen.gen.c.structural.test;

import java.io.IOException;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.structural.test.util.AbstractTest;

public class TestCUnit extends AbstractTest {

	@Test
	public void cEmpty() {
		testStructuralCFile("empty", "empty", "emptyC");
	}

	@Test
	public void hEmpty() {
		testStructuralHFile("empty", "empty", "emptyH");
	}

	@Test
	public void cAndHEmpty() {
		testStructuralFiles("empty", "empty", "emptyHandC");
	}
}
