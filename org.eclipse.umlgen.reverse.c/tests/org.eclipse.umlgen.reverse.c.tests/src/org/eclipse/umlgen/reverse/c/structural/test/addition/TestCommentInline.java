package org.eclipse.umlgen.reverse.c.structural.test.addition;

import static org.eclipse.umlgen.reverse.c.structural.test.utils.TestUtils.getResourceInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.editors.text.TextEditor;
import org.junit.Test;
import org.eclipse.umlgen.reverse.c.structural.test.utils.AbstractTest;

public class TestCommentInline extends AbstractTest {
	@Test
	public void testinlineParametersInC() throws CoreException,
			InterruptedException {

		IProject project = createIProject("/testinlineParametersC",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("inlineParameters.c"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, " ");

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/comment/inlineParameters.c"));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/addition/comment/inlineParametersC.uml");
	}

	@Test
	public void testinlineParametersInH() throws CoreException,
			InterruptedException {

		IProject project = createIProject("/testinlineParametersH",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("inlineParameters.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, " ");

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/comment/inlineParameters.h"));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/addition/comment/inlineParametersH.uml");
	}

}
