/*******************************************************************************
 * Copyright (c) 2010, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Christophe Le Camus (CS) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.structural.test.addition;

import static org.eclipse.umlgen.reverse.c.structural.test.utils.TestUtils.getResourceInputStream;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.umlgen.reverse.c.structural.test.utils.AbstractTest;
import org.junit.Test;

public class TestTypeDef2 extends AbstractTest {

	@Test
	public void testMultipleSimpleInC() throws CoreException, InterruptedException {

		IProject project = createIProject("testTypeDefMultipleSimpleC", new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("simple.c"), new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(editor,
				getResourceInputStream("/resource/structural/addition/typedef/multiple/simple.c"));

		closeEditor(editor, true);

		testModel(project, "/resource/structural/addition/typedef/multiple/TypeDefMultipleSimpleC.uml");
	}

	@Test
	public void testMultipleSimpleInH() throws CoreException, InterruptedException {

		IProject project = createIProject("testTypeDefMultipleSimpleH", new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("simple.h"), new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(editor,
				getResourceInputStream("/resource/structural/addition/typedef/multiple/simple.h"));

		closeEditor(editor, true);

		testModel(project, "/resource/structural/addition/typedef/multiple/TypeDefMultipleSimpleH.uml");
	}

	@Test
	public void testMultipleArrayInC() throws CoreException, InterruptedException {

		IProject project = createIProject("testTypeDefMultipleArrayC", new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("array.c"), new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(editor,
				getResourceInputStream("/resource/structural/addition/typedef/multiple/array.c"));

		closeEditor(editor, true);

		testModel(project, "/resource/structural/addition/typedef/multiple/TypeDefMultipleArrayC.uml");
	}

	@Test
	public void testMultipleArrayInH() throws CoreException, InterruptedException {

		IProject project = createIProject("testTypeDefMultipleArrayH", new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("array.h"), new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(editor,
				getResourceInputStream("/resource/structural/addition/typedef/multiple/array.h"));

		closeEditor(editor, true);

		testModel(project, "/resource/structural/addition/typedef/multiple/TypeDefMultipleArrayH.uml");
	}

	@Test
	public void testMultiplePointerInC() throws CoreException, InterruptedException {

		IProject project = createIProject("testTypeDefMultiplePointerC", new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("pointer.c"), new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(editor,
				getResourceInputStream("/resource/structural/addition/typedef/multiple/pointer.c"));

		closeEditor(editor, true);

		testModel(project, "/resource/structural/addition/typedef/multiple/TypeDefMultiplePointerC.uml");
	}

	@Test
	public void testMultiplePointerInH() throws CoreException, InterruptedException {

		IProject project = createIProject("testTypeDefMultiplePointerH", new NullProgressMonitor());

		IFile iFile = createIFile(project, new Path("pointer.h"), new NullProgressMonitor());

		TextEditor editor = openEditor(iFile);

		setEditorContent(editor, new ByteArrayInputStream(" ".getBytes()));

		setEditorContent(editor,
				getResourceInputStream("/resource/structural/addition/typedef/multiple/pointer.h"));

		closeEditor(editor, true);

		testModel(project, "/resource/structural/addition/typedef/multiple/TypeDefMultiplePointerH.uml");
	}
}
