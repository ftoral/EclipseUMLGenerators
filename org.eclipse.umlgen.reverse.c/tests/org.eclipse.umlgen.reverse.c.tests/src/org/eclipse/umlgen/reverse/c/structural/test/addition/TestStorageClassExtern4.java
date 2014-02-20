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

public class TestStorageClassExtern4 extends AbstractTest {

	@Test
	public void testStorageClassExtern4InC() throws CoreException,
			InterruptedException {

		IProject project = createIProject("/testextern4C",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("extern4.c"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/storageClasses/extern4.c"));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/addition/storageClasses/extern4C.uml");

	}

	@Test
	public void testStorageClassExtern4InH() throws CoreException,
			InterruptedException {

		IProject project = createIProject("/testextern4H",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("extern4.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/storageClasses/extern4.h"));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/addition/storageClasses/extern4H.uml");

	}

}
