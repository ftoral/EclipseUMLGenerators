package org.eclipse.umlgen.reverse.c.structural.test.addition;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.junit.Test;
import org.eclipse.umlgen.reverse.c.structural.test.utils.AbstractTest;

public class TestCUnit extends AbstractTest {

	@Test
	public void testEmptyHandC() throws CoreException, InterruptedException {
		IProject project = createIProject("testemptyHandC",
				new NullProgressMonitor());

		IFile cFile = createIFile(project, new Path("empty.c"),
				new NullProgressMonitor());
		IFile hFile = createIFile(project, new Path("empty.h"),
				new NullProgressMonitor());

		testModel(project, "/resource/structural/addition/empty/emptyHandC.uml");
	}

	@Test
	public void testEmptyC() throws CoreException, InterruptedException {
		IProject project = createIProject("testemptyC",
				new NullProgressMonitor());

		createIFile(project, new Path("empty.c"), new NullProgressMonitor());

		testModel(project, "/resource/structural/addition/empty/emptyC.uml");
	}

	@Test
	public void testEmptyH() throws CoreException, InterruptedException {
		IProject project = createIProject("testemptyH",
				new NullProgressMonitor());

		createIFile(project, new Path("empty.h"), new NullProgressMonitor());

		testModel(project, "/resource/structural/addition/empty/emptyH.uml");
	}
}
