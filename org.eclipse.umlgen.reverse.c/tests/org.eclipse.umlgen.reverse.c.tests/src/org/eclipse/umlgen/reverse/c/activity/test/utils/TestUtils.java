/*******************************************************************************
 * Copyright (c) 2010, 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Stephane Thibaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.activity.test.utils;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.parser.CodeReader;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.NullLogService;
import org.eclipse.cdt.core.parser.ScannerInfo;
import org.eclipse.cdt.internal.core.dom.NullCodeReaderFactory;
import org.eclipse.cdt.internal.core.index.EmptyCIndex;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;

public class TestUtils {

	private static IScannerInfo scanInfo = new ScannerInfo();

	private static IParserLogService logService = new NullLogService();

	public static Model getUMLModel(ResourceSet rs, String path) {
		URL modelURL = getResource(path);
		Resource modelResource = rs.getResource(URI.createURI(modelURL.getFile()), true);
		InputStream inputStream = null;
		Model ret = null;
		try {
			inputStream = modelURL.openStream();

			List<EObject> contents = modelResource.getContents();

			if (contents.size() > 0) {
				EObject content0 = contents.get(0);
				if (content0 instanceof Model) {
					ret = (Model)content0;
				} else {
					throw new IllegalArgumentException("first element of the resource is not a UML Model");
				}
			} else {
				throw new IllegalArgumentException("no content in the given resource");
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("error while opening the model stream", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new IllegalArgumentException("error while closing the model stream", e);
				}
			}
		}

		return ret;
	}

	@SuppressWarnings("restriction")
	public static IASTTranslationUnit getTranslationUnit(String cFilePath) {
		URL tuURL = getResource(cFilePath);
		ILanguage lang = GCCLanguage.getDefault();
		InputStream inputStream = null;
		IASTTranslationUnit ast = null;
		try {
			inputStream = tuURL.openStream();
			ast = lang.getASTTranslationUnit(new CodeReader(tuURL.toURI().getPath(), inputStream), scanInfo,
					NullCodeReaderFactory.getInstance(), EmptyCIndex.INSTANCE, logService);
		} catch (CoreException e) {
			throw new IllegalStateException("something goes wrong with CoreModel", e);
		} catch (IOException e) {
			throw new IllegalStateException("can not open input stream of TU", e);
		} catch (URISyntaxException e) {
			throw new IllegalStateException("can not convert FilePath into URI", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new IllegalStateException("can not close input stream of TU", e);
				}
			}
		}

		return ast;
	}

	public static void assertEquals(EObject expected, EObject actual) {
		// FIXME MIGRATION reference to EMF Compare 1.x (to migrate to 2.x)
		// Map<String, Object> options = ImmutableMap.<String, Object>
		// builder().put(MatchOptions.OPTION_IGNORE_XMI_ID,
		// Boolean.TRUE).build();
		// MatchModel match;
		// try
		// {
		// match = MatchService.doMatch(expected, actual, options);
		// DiffModel diff = DiffService.doDiff(match, false);
		// if (diff.getDifferences().size() > 0)
		// {
		// StringBuilder sb = new StringBuilder("Models do not match\n");
		// for (DiffElement de : diff.getDifferences())
		// {
		// sb.append(toString(de));
		// }
		// try
		// {
		// throw new ComparisonFailure(sb.toString(),
		// EResources.serialize(expected), EResources.serialize(actual));
		// }
		// catch (IOException e)
		// {
		// throw new AssertionError(sb.toString());
		// }
		// }
		// }
		// catch (InterruptedException e)
		// {
		// throw new AssertionError(e);
		// }
	}

	/**
	 * Returns the first function in the {@link IASTTranslationUnit}.
	 *
	 * @param unit
	 * @return
	 */
	public static IASTFunctionDefinition getFirstFunctionInUnit(IASTTranslationUnit unit) {
		IASTDeclaration[] declarations = unit.getDeclarations(true);
		IASTFunctionDefinition ret = null;

		for (IASTDeclaration declaration : declarations) {
			if (declaration instanceof IASTFunctionDefinition) {
				ret = (IASTFunctionDefinition)declaration;
				break;
			}
		}

		return ret;
	}

	public static Model createOutputModel(ResourceSet rs, Model testModel) {
		Model outputModel = UMLFactory.eINSTANCE.createModel();
		outputModel.setName(testModel.getName());
		Resource actualResource = rs.createResource(URI.createURI("actual.uml"));
		actualResource.getContents().add(outputModel);
		return outputModel;
	}

	public static void saveGeneratedModel(Model model, ResourceSet resourceSet, String filename) {
		saveModel(model, resourceSet, "resource/" + getSuffixedFilename(filename, "_actual"));
	}

	public static String getSuffixedFilename(String filename, String suffix) {
		int dotPos = filename.lastIndexOf(".");
		return filename.substring(0, dotPos) + suffix + filename.substring(dotPos);
	}

	public static void saveModel(Model model, ResourceSet resourceSet, String filename) {
		Resource resource = resourceSet.createResource(URI.createFileURI(filename));
		resource.getContents().add(model);
		try {
			resource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// FIXME MIGRATION reference to EMF Compare 1.x (to migrate to 2.x)
	// private static StringBuilder toString(DiffElement de)
	// {
	// return toString(de, 0);
	// }
	//
	// private static StringBuilder toString(DiffElement de, int depth)
	// {
	// StringBuilder sb = new StringBuilder();
	//
	// for (int i = 0; i <= depth; i++)
	// {
	// sb.append("  ");
	// }
	// sb.append(de.toString()).append("\n");
	//
	// for (DiffElement subDiff : de.getSubDiffElements())
	// {
	// sb.append(toString(subDiff, depth + 1));
	// }
	// return sb;
	// }

	private static URL getResource(String resourceName) {
		URL url = TestUtils.class.getClassLoader().getResource(resourceName);
		Preconditions.checkArgument(url != null, "resource %s not found.", resourceName);
		return url;
	}
}
