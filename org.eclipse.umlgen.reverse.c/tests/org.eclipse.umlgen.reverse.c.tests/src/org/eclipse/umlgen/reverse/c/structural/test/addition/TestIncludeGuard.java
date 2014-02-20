package org.eclipse.umlgen.reverse.c.structural.test.addition;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.editors.text.TextEditor;
import org.junit.Test;
import org.eclipse.umlgen.reverse.c.structural.test.utils.AbstractTest;

public class TestIncludeGuard extends AbstractTest {

	@Test
	public void testIncludeGuard() throws CoreException, InterruptedException {
		IProject project = createIProject("testincludeGuard1",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("aFile.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, " ");

		StringBuilder sb = new StringBuilder("#ifndef AFILE_H__\n");

		setEditorContent(editor, sb.toString());

		testModel(project,
				"/resource/structural/addition/includeGuard/includeGuard1.uml");

		closeEditor(editor, true);

	}

	@Test
	public void testIncludeGuard2() throws CoreException, InterruptedException {

		IProject project = createIProject("testincludeGuard2",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("aFile.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, " ");

		StringBuilder sb = new StringBuilder("#ifndef AFILE_H__\n");

		setEditorContent(editor, sb.toString());

		sb.append("#define AFILE_H__\n");

		setEditorContent(editor, sb.toString());

		testModel(project,
				"/resource/structural/addition/includeGuard/includeGuard2.uml");

		closeEditor(editor, true);

	}

	@Test
	public void testIncludeGuard3() throws CoreException, InterruptedException {

		IProject project = createIProject("testincludeGuard3",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("aFile.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, " ");

		StringBuilder sb = new StringBuilder("#ifndef AFILE_H__\n");

		setEditorContent(editor, sb.toString());

		sb.append("#define AFILE_H__\n");

		setEditorContent(editor, sb.toString());

		sb.append("\n\n\n\n");

		setEditorContent(editor, sb.toString());

		testModel(project,
				"/resource/structural/addition/includeGuard/includeGuard3.uml");

		closeEditor(editor, true);

	}

	@Test
	public void testIncludeGuard4() throws CoreException, InterruptedException {

		IProject project = createIProject("testincludeGuard4",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("aFile.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, " ");

		StringBuilder sb = new StringBuilder("#ifndef AFILE_H__\n");

		setEditorContent(editor, sb.toString());

		sb.append("#define AFILE_H__\n");

		setEditorContent(editor, sb.toString());

		sb.append("\n\n\n\n");

		setEditorContent(editor, sb.toString());

		sb.append("#endif");

		setEditorContent(editor, sb.toString());

		testModel(project,
				"/resource/structural/addition/includeGuard/includeGuard4.uml");

		closeEditor(editor, true);
	}

}
