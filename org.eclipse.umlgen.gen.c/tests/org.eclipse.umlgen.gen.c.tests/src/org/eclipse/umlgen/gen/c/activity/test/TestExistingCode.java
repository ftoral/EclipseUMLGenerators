package org.eclipse.umlgen.gen.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.gen.c.activity.test.util.AbstractTest;

public class TestExistingCode extends AbstractTest {

	@Test
	public void testBenchmark() {
		testUmlActivityFile("existingCode/testBenchmark.uml");
	}

	@Test
	public void testCoreSetValues() {
		testUmlActivityFile("existingCode/testCoreSetValues.uml");
	}

	@Test
	public void testGioFileCtor() {
		testUmlActivityFile("existingCode/testGioFileCtor.uml");
	}

	@Test
	public void testGioMemoryFileCtorWithAmpersand() {
		testUmlActivityFile("existingCode/testGioMemoryFileCtorWithAmpersand.uml");
	}

	@Test
	public void testGioMemoryFileSeek() {
		testUmlActivityFile("existingCode/testGioMemoryFileSeek.uml");
	}

	@Test
	public void testG_object_notify_queue_thaw() {
		testUmlActivityFile("existingCode/testG_object_notify_queue_thaw.uml");
	}

	@Test
	public void test_XtTableAddConverter() {
		testUmlActivityFile("existingCode/test_XtTableAddConverter.uml");
	}
}
