package org.eclipse.umlgen.reverse.c.activity.test;

import org.junit.Test;
import org.eclipse.umlgen.reverse.c.activity.test.utils.AbstractTest;

public class TestExistingCode extends AbstractTest {

	@Test
	public void testGioFileCtor() {
		testCFile("existingCode/testGioFileCtor.c", false);
	}

	@Test
	public void testGioMemoryFileCtorWithAmpersand() {
		testCFile("existingCode/testGioMemoryFileCtorWithAmpersand.c", false);
	}

	@Test
	public void testGioMemoryFileSeek() {
		testCFile("existingCode/testGioMemoryFileSeek.c", false);
	}

	@Test
	public void test_XtTableAddConverter() {
		testCFile("existingCode/test_XtTableAddConverter.c", false);
	}

	@Test
	public void testCoreSetValues() {
		testCFile("existingCode/testCoreSetValues.c", false);
	}

	@Test
	public void testBenchmark() {
		testCFile("existingCode/testBenchmark.c", false);
	}

	@Test
	public void testG_object_notify_queue_thaw() {
		testCFile("existingCode/testG_object_notify_queue_thaw.c", false);
	}
}
