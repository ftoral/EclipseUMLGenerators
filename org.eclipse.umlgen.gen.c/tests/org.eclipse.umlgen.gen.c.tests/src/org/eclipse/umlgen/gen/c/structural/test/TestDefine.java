package org.eclipse.umlgen.gen.c.structural.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.structural.test.util.AbstractTest;

public class TestDefine extends AbstractTest {

	@Test
	public void cEmptyDefine() {
		testStructuralCFile("define", "aFile", "defineemptyC");
	}

	@Test
	public void hEmptyDefine() {
		testStructuralHFile("define", "aFile", "defineemptyH");
	}

	@Test
	public void cIntDefine() {
		testStructuralCFile("define", "aFile", "intDefineC");
	}

	@Test
	public void hIntDefine() {
		testStructuralHFile("define", "aFile", "intDefineH");
	}

	@Test
	public void cStringDefine() {
		testStructuralCFile("define", "aFile", "stringDefineC");
	}

	@Test
	public void hStringDefine() {
		testStructuralHFile("define", "aFile", "stringDefineH");
	}
}
