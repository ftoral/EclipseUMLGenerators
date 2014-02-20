package org.eclipse.umlgen.reverse.c.structural.test.removal;

import static org.eclipse.umlgen.reverse.c.structural.test.utils.TestUtils.getResourceInputStream;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.editors.text.TextEditor;
import org.junit.Test;
import org.eclipse.umlgen.reverse.c.structural.test.utils.AbstractTest;

public class TestOperation extends AbstractTest {
	@Test
	public void testOperationInC() throws CoreException, InterruptedException {

		IProject project = createIProject("testRemovalOfOperationC",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("operation.c"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/operation/operation.c"));

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/removal/operation/RemovalOfOperationC.uml");
	}

	@Test
	public void testOperationInH() throws CoreException, InterruptedException {

		IProject project = createIProject("testRemovalOfOperationH",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("operation.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/operation/operation.h"));

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/removal/operation/RemovalOfOperationH.uml");
	}

}
