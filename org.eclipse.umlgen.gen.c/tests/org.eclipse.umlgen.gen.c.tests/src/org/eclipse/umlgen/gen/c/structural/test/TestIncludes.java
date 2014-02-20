package org.eclipse.umlgen.gen.c.structural.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.structural.test.util.AbstractTest;

public class TestIncludes extends AbstractTest {

	@Test
	public void includesExtH() {
		testStructuralHFile("includes", "file", "includesExtH");
	}

	@Test
	public void includesExtC() {
		testStructuralCFile("includes", "file", "includesExtC");
	}

	@Test
	public void sameNameExternalC() {
		testStructuralCFile("includes", "sameNameExternal", "sameNameExternalC");
	}

	@Test
	public void sameNameInternalC() {
		testStructuralCFile("includes", "sameNameInternal", "sameNameInternalC");
	}
}
