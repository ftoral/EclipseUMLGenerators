package org.eclipse.umlgen.gen.c.structural.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.structural.test.util.AbstractTest;

public class TestComment extends AbstractTest {

	@Test
	public void commentDuplicataClass() {
		testStructuralFiles("comment", "commentDuplicataClass",
				"commentDuplicataClassE");
	}

	@Test
	public void duplicataOperation() {
		testStructuralFiles("comment", "commentDuplicataOperation",
				"commentDuplicataOperationN");
	}

	@Test
	public void commentClasseC() {
		testStructuralCFile("comment", "commentClasse", "commentClasseC");
	}

	@Test
	public void commentClasseH() {
		testStructuralHFile("comment", "commentClasse", "commentClasseH");
	}

	@Test
	public void commentIncludeC() {
		testStructuralCFile("comment", "commentInclude", "commentIncludeC");
	}

	@Test
	public void commentIncludeH() {
		testStructuralHFile("comment", "commentInclude", "commentIncludeH");
	}

	@Test
	public void commentOperationC() {
		testStructuralCFile("comment", "CommentOperation", "CommentOperationC");
	}

	@Test
	public void commentOperationH() {
		testStructuralHFile("comment", "CommentOperation", "CommentOperationH");
	}

	@Test
	public void commentInlineParametersC() {
		testStructuralCFile("comment", "inlineParameters", "inlineParametersC");
	}

	@Test
	public void commentInlineParametersH() {
		testStructuralHFile("comment", "inlineParameters", "inlineParametersH");
	}
}
