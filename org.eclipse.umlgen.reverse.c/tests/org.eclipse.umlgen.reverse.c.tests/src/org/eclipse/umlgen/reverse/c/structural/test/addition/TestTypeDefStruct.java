package org.eclipse.umlgen.reverse.c.structural.test.addition;

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

public class TestTypeDefStruct extends AbstractTest {
	@Test
	public void testTypeDefStructInC() throws CoreException,
			InterruptedException {

		IProject project = createIProject("testTypeDefStructC",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("struct.c"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/typedef/struct/struct.c"));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/addition/typedef/struct/TypeDefStructC.uml");
	}

	@Test
	public void testTypeDefStructInH() throws CoreException,
			InterruptedException {

		IProject project = createIProject("testTypeDefStructH",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("struct.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/typedef/struct/struct.h"));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/addition/typedef/struct/TypeDefStructH.uml");
	}

}
