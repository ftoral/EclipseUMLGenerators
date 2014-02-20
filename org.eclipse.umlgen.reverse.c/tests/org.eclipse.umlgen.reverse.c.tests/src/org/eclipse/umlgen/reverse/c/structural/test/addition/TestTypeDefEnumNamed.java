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

public class TestTypeDefEnumNamed extends AbstractTest {
	@Test
	public void testTypeDefEnumNamedInC() throws CoreException,
			InterruptedException {

		IProject project = createIProject("testTypeDefEnumNamedC",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("namedEnum.c"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/typedef/named/enumeration/namedEnum.c"));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/addition/typedef/named/enumeration/TypeDefEnumNamedC.uml");
	}

	@Test
	public void testTypeDefEnumNamedInH() throws CoreException,
			InterruptedException {

		IProject project = createIProject("testTypeDefEnumNamedH",
				new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("namedEnum.h"),
				new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(
				editor,
				getResourceInputStream("/resource/structural/addition/typedef/named/enumeration/namedEnum.h"));

		closeEditor(editor, true);

		testModel(project,
				"/resource/structural/addition/typedef/named/enumeration/TypeDefEnumNamedH.uml");
	}

}
