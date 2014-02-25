/*******************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Christophe Le Camus (CS) - initial API and implementation 
 *     Sebastien Gabel (CS) - evolutions
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.reconciler;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTCompositeTypeSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTElaboratedTypeSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTNamedTypeSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIfndefStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorStatement;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IFunction;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.IEnumeration;
import org.eclipse.cdt.core.model.IInclude;
import org.eclipse.cdt.core.model.IStructure;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.core.model.ITypeDef;
import org.eclipse.cdt.core.model.IVariable;
import org.eclipse.cdt.core.model.IVariableDeclaration;
import org.eclipse.cdt.core.model.IWorkingCopy;
import org.eclipse.cdt.internal.core.dom.parser.ASTQueries;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.umlgen.reverse.c.event.CModelChangedEvent;
import org.eclipse.umlgen.reverse.c.event.FunctionBodyChanged;
import org.eclipse.umlgen.reverse.c.event.FunctionDefinitionRenamed;
import org.eclipse.umlgen.reverse.c.event.FunctionParameterChanged;
import org.eclipse.umlgen.reverse.c.internal.beans.FunctionParameter;
import org.eclipse.umlgen.reverse.c.internal.bundle.Activator;
import org.eclipse.umlgen.reverse.c.internal.reconciler.BindingResolver;
import org.eclipse.umlgen.reverse.c.internal.reconciler.CFileReconciler;
import org.eclipse.umlgen.reverse.c.internal.reconciler.HFileReconciler;
import org.eclipse.umlgen.reverse.c.internal.reconciler.IFileReconciler;
import org.eclipse.umlgen.reverse.c.internal.reconciler.SameFileLocation;
import org.eclipse.umlgen.reverse.c.internal.reconciler.Utils;
import org.eclipse.umlgen.reverse.c.util.ASTUtil;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;

/**
 * This reconciler is registered when the plugin starts. It is in charge of
 * creating corresponding events and to notify them when a C AST model changes.
 * For this reason, the notification is always done with the need to save the
 * UML model loaded into the model manager once modified (Cf.
 * notifyListeners(event, true);)..
 */
@SuppressWarnings("restriction")
public class CASTReconciler extends AbstractReconciler {
	private boolean includeInactiveNodes;

	private CFileReconciler cFileReconciler;

	private HFileReconciler hFileReconciler;

	private static Function<IASTNode, String> rawSignature = new Function<IASTNode, String>() {
		public String apply(IASTNode from) {
			return from.getRawSignature();
		}
	};

	private static Function<IASTPreprocessorMacroDefinition, String> macroName = new Function<IASTPreprocessorMacroDefinition, String>() {
		public String apply(IASTPreprocessorMacroDefinition from) {
			return from.getName().toString();
		}
	};

	public CASTReconciler() {
		super();
		includeInactiveNodes = true;
		cFileReconciler = new CFileReconciler();
		hFileReconciler = new HFileReconciler();
	}

	public boolean isIncludeInactiveNodes() {
		return includeInactiveNodes;
	}

	public void setIncludeInactiveNodes(boolean include) {
		includeInactiveNodes = include;
	}

	protected IFileReconciler getFileReconciler(IASTDeclaration declaration) {
		return getFileReconciler(declaration.getTranslationUnit());
	}

	/**
	 * Gets the right {@link IFileReconciler} according to the kind of processed
	 * file (H or C).
	 * 
	 * @param tu
	 *            The current translation unit
	 * @return A {@link CFileReconciler} or a {@link HFileReconciler} instance.
	 */
	protected IFileReconciler getFileReconciler(IASTTranslationUnit tu) {
		return tu.isHeaderUnit() ? hFileReconciler : cFileReconciler;
	}

	protected Collection<IASTDeclaration> getAddedASTDeclaration(
			IASTTranslationUnit originalTranslationUnit,
			IASTTranslationUnit newTranslationUnit) {
		IASTDeclaration[] newDecls = newTranslationUnit
				.getDeclarations(includeInactiveNodes);
		IASTDeclaration[] originalDecls = originalTranslationUnit
				.getDeclarations(includeInactiveNodes);
		return Utils.inLeftOnly(newDecls, originalDecls, rawSignature);
	}

	protected Collection<IASTDeclaration> getRemovedASTDeclaration(
			IASTTranslationUnit originalTranslationUnit,
			IASTTranslationUnit newTranslationUnit) {
		return getAddedASTDeclaration(newTranslationUnit,
				originalTranslationUnit);
	}

	private boolean firstIfndefMacro(IASTPreprocessorStatement ifndefstatement,
			IASTTranslationUnit astWorking) {
		// avoid to treat multiple #ifndef
		IASTPreprocessorStatement[] statements = astWorking
				.getAllPreprocessorStatements();
		int currentMacro = statements.length - 1;
		int firstMacro = statements.length - 1;
		for (int i = statements.length - 1; i > -1; i--) {
			if (statements[i].getRawSignature().equals(
					ifndefstatement.getRawSignature())) {
				currentMacro = i;
				firstMacro = i;
			} else {
				firstMacro = i;
			}
		}
		if (firstMacro == currentMacro) {

			int astifndefPosition = ifndefstatement.getFileLocation()
					.getNodeOffset();
			int firstNodePosition = astWorking.getFileLocation()
					.getNodeLength();
			if (astWorking.getDeclarations().length > 0
					&& astWorking.getDeclarations()[0] instanceof IASTNode) {
				firstNodePosition = ((IASTNode) astWorking.getDeclarations()[0])
						.getFileLocation().getNodeOffset();
			}
			// only if the #ifndef is before the first instruction
			if (astifndefPosition <= firstNodePosition) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see org.eclipse.umlgen.reverse.c.reconciler.AbstractReconciler#addedElement(org.eclipse.cdt.core.model.ITranslationUnit,
	 *      org.eclipse.cdt.core.model.IWorkingCopy,
	 *      org.eclipse.cdt.core.model.ICElement)
	 */
	@Override
	public void addedElement(ITranslationUnit originalUnit,
			IWorkingCopy workingUnit, ICElement element) throws CoreException {
		CModelChangedEvent event = null;
		IASTTranslationUnit astWorking = workingUnit.getAST();
		switch (element.getElementType()) {
		// #include
		case ICElement.C_INCLUDE:
			IASTPreprocessorIncludeStatement include = getASTIncludeOf(element,
					astWorking);
			event = getFileReconciler(astWorking).addElement(include,
					workingUnit);
			break;
		// #define constante initialization value
		case ICElement.C_MACRO:
			IASTPreprocessorMacroDefinition astMacro = getASTMacroDeclarationOf(
					element, astWorking);
			event = getFileReconciler(astWorking).addElement(astMacro,
					workingUnit);
			break;
		default:
			break;
		}
		notifyListeners(event, true);

	}

	public void modifiedElement(ITranslationUnit originalUnit,
			ITranslationUnit workingUnit, ICElement oldElement,
			ICElement newElement) {
		switch (oldElement.getElementType()) {
		case ICElement.C_FUNCTION:
			modifiedElement(originalUnit, workingUnit,
					(org.eclipse.cdt.core.model.IFunction) oldElement,
					(org.eclipse.cdt.core.model.IFunction) newElement);
			break;
		case ICElement.C_INCLUDE:
			modifiedElement(originalUnit, workingUnit, (IInclude) oldElement,
					(IInclude) newElement);
			break;
		default:
			System.out.println("Unhandled modification of "
					+ oldElement.getClass().getSimpleName());
			break;
		}
	}

	private void modifiedElement(ITranslationUnit originalUnit,
			ITranslationUnit workingUnit, IInclude oldElement,
			IInclude newElement) {
		try {
			IASTPreprocessorIncludeStatement oldInclude = getASTIncludeOf(
					oldElement, originalUnit.getAST());
			CModelChangedEvent event = getFileReconciler(originalUnit.getAST())
					.removeElement(oldInclude, originalUnit);
			notifyListeners(event, true);
			IASTTranslationUnit astWorking = workingUnit.getAST();
			IASTPreprocessorIncludeStatement include = getASTIncludeOf(
					newElement, astWorking);
			event = getFileReconciler(astWorking).addElement(include,
					workingUnit);
			notifyListeners(event, true);
		} catch (CoreException e) {
			Activator.log("Core Exception : " + e.getMessage(), IStatus.ERROR);
		}
	}

	private void modifiedElement(ITranslationUnit originalUnit,
			ITranslationUnit workingUnit,
			org.eclipse.cdt.core.model.IFunction oldElement,
			org.eclipse.cdt.core.model.IFunction newElement) {
		Collection<IASTDeclaration> elementRemoved = null;
		Collection<IASTDeclaration> elementAdded = null;
		try {
			elementRemoved = getRemovedASTDeclaration(originalUnit.getAST(),
					workingUnit.getAST());
			elementAdded = getAddedASTDeclaration(originalUnit.getAST(),
					workingUnit.getAST());
		} catch (CoreException e) {
			Activator.log("Error : " + e.getMessage(), IStatus.ERROR);
		}

		if (!oldElement.getElementName().equals(newElement.getElementName())) {
			CModelChangedEvent event = FunctionDefinitionRenamed.builder()
					.previousName(oldElement.getElementName())
					.currentName(newElement.getElementName())
					.translationUnit(newElement.getTranslationUnit()).build();
			notifyListeners(event, true);
		} else if (!oldElement.getReturnType().equals(
				newElement.getReturnType())) {
			// oldElement.getReturnType();
			// FunctionReturnTypeChanged event = null; //
			// FunctionReturnTypeChanged.builder();
			// notifyListeners(event, true);
		}
		if (!(oldElement.getNumberOfParameters() == newElement
				.getNumberOfParameters())) {
			IASTFunctionDefinition oldAstFunction = (IASTFunctionDefinition) elementRemoved;
			// IASTFunctionDefinition newAstFunction = (IASTFunctionDefinition)
			// elementAdded;

			List<FunctionParameter> parameters = ASTUtil
					.collectParameterInformation(oldAstFunction.getDeclarator());
			CModelChangedEvent event = FunctionParameterChanged.builder()
					.functionName(newElement.getElementName())
					.setParameters(parameters)
					.translationUnit(newElement.getTranslationUnit()).build();
			notifyListeners(event, true);
		} else {
			System.out
					.println("Do not see the difference between old function : ");
			System.out.println(oldElement.toString());
			System.out.println(" and ");
			System.out.println(newElement.toString());
		}
	}

	private Collection<IASTPreprocessorMacroDefinition> findPreProcessingMacroAdded(
			IASTTranslationUnit translationUnitAST,
			IASTTranslationUnit workingUnitAST) {
		IASTPreprocessorMacroDefinition[] oldMacros = translationUnitAST
				.getMacroDefinitions();
		IASTPreprocessorMacroDefinition[] newMacros = workingUnitAST
				.getMacroDefinitions();

		// FIXME MIGRATION use of Google API
		// Collection<IASTPreprocessorMacroDefinition> oldMacrosFiltered =
		// Collections2.filter(ImmutableList.of(oldMacros), new
		// SameFileLocation(translationUnitAST));
		// Collection<IASTPreprocessorMacroDefinition> newMacrosFiltered =
		// Collections2.filter(ImmutableList.of(newMacros), new
		// SameFileLocation(workingUnitAST));
		Collection<IASTPreprocessorMacroDefinition> oldMacrosFiltered = Collections.EMPTY_LIST;
		Collection<IASTPreprocessorMacroDefinition> newMacrosFiltered = Collections.EMPTY_LIST;
		return Utils
				.inLeftOnly(newMacrosFiltered, oldMacrosFiltered, macroName);
	}

	private Collection<IASTPreprocessorStatement> findPreProcessingStatementAdded(
			IASTTranslationUnit translationUnitAST,
			IASTTranslationUnit workingUnitAST) {
		IASTPreprocessorStatement[] oldStatements = translationUnitAST
				.getAllPreprocessorStatements();
		IASTPreprocessorStatement[] newStatements = workingUnitAST
				.getAllPreprocessorStatements();

		// FIXME MIGRATION use of Google API
		// Collection<IASTPreprocessorStatement> oldStatementsFiltered =
		// Collections2.filter(ImmutableList.of(oldStatements), new
		// SameFileLocation(translationUnitAST));
		// Collection<IASTPreprocessorStatement> newStatementsFiltered =
		// Collections2.filter(ImmutableList.of(newStatements), new
		// SameFileLocation(workingUnitAST));
		Collection<IASTPreprocessorStatement> oldStatementsFiltered = Collections.EMPTY_LIST;
		Collection<IASTPreprocessorStatement> newStatementsFiltered = Collections.EMPTY_LIST;

		return Utils.inLeftOnly(newStatementsFiltered, oldStatementsFiltered,
				rawSignature);
	}

	/**
	 * @see org.eclipse.umlgen.reverse.c.reconciler.AbstractReconciler#removedElement(org.eclipse.cdt.core.model.ITranslationUnit,
	 *      org.eclipse.cdt.core.model.IWorkingCopy,
	 *      org.eclipse.cdt.core.model.ICElement)
	 */
	@Override
	public void removedElement(ITranslationUnit originalUnit,
			IWorkingCopy workingUnit, ICElement element) throws CoreException {
		CModelChangedEvent event = null;
		IASTTranslationUnit astWorking = originalUnit.getAST();

		switch (element.getElementType()) {
		case ICElement.C_INCLUDE:
			// IInclude include = findPreProcessingIncludeAdded(workingUnit,
			// originalUnit);
			IASTPreprocessorIncludeStatement include = getASTIncludeOf(element,
					astWorking);
			event = getFileReconciler(astWorking).removeElement(include,
					originalUnit);
			break;
		case ICElement.C_MACRO:
			IASTPreprocessorMacroDefinition astMacro = getASTMacroDeclarationOf(
					element, astWorking);
			event = getFileReconciler(astWorking).removeElement(astMacro,
					originalUnit);
			break;
		default:
			Activator.log(
					"CASTReconciler.removedElement() - ignored ICElement removal: "
							+ element, IStatus.WARNING);
			break;
		}

		notifyListeners(event, true);
	}

	private IASTPreprocessorMacroDefinition getASTMacroDeclarationOf(
			ICElement coreElement, IASTTranslationUnit ast) {
		for (IASTPreprocessorMacroDefinition macro : ast.getMacroDefinitions()) {
			if (macro.getName().toString().equals(coreElement.getElementName())) {
				return macro;
			}
		}
		return null;
	}

	private IASTPreprocessorIncludeStatement getASTIncludeOf(
			ICElement coreElement, IASTTranslationUnit ast) {
		for (IASTPreprocessorIncludeStatement include : ast
				.getIncludeDirectives()) {
			if (include.getName().toString()
					.equals(coreElement.getElementName())) {
				return include;
			}
		}
		return null;
	}

	/**
	 * @see org.eclipse.umlgen.reverse.c.reconciler.AbstractReconciler#addedElement(org.eclipse.cdt.core.dom.ast.IASTTranslationUnit,
	 *      org.eclipse.cdt.core.dom.ast.IASTTranslationUnit,
	 *      org.eclipse.cdt.core.model.ITranslationUnit,
	 *      org.eclipse.cdt.core.model.ICElement)
	 */
	@Override
	public void addedElement(IASTTranslationUnit originalTranslationUnit,
			IASTTranslationUnit newTranslationUnit, ITranslationUnit tu,
			ICElement coreElement) throws CoreException {
		Collection<IASTDeclaration> elements = getAddedASTDeclaration(
				originalTranslationUnit, newTranslationUnit);
		CModelChangedEvent event = null;
		IASTDeclaration element = null;

		switch (coreElement.getElementType()) {
		case ICElement.C_VARIABLE_DECLARATION:
			element = BindingResolver.resolveBindingIASTSimpleDeclaration(
					elements, coreElement);
			event = getFileReconciler(element).addElement(
					(IASTSimpleDeclaration) element,
					(IVariableDeclaration) coreElement);
			break;
		case ICElement.C_VARIABLE:
			element = BindingResolver.resolveBindingIASTSimpleDeclaration(
					elements, coreElement);
			event = getFileReconciler(element).addElement(
					(IASTSimpleDeclaration) element, (IVariable) coreElement);
			break;
		case ICElement.C_ENUMERATION:
			element = BindingResolver.resolveBindingIASTEnumeration(elements,
					coreElement, tu);
			event = getFileReconciler(newTranslationUnit)
					.addElement(
							(IASTEnumerationSpecifier) (((IASTSimpleDeclaration) element)
									.getDeclSpecifier()),
							(IEnumeration) coreElement);
			break;
		case ICElement.C_FUNCTION_DECLARATION:
			// without body : operation
			element = BindingResolver.resolveBindingIASTFunctionDeclarator(
					elements, coreElement);
			event = getFileReconciler(element)
					.addElement(
							((IASTFunctionDeclarator) ((IASTSimpleDeclaration) element)
									.getDeclarators()[0]),
							(org.eclipse.cdt.core.model.IFunctionDeclaration) coreElement);
			break;
		case ICElement.C_FUNCTION:
			// body : -> OpaqueBehavior
			element = BindingResolver.resolveBindingIASTFunctionDefinition(
					elements, coreElement);
			event = getFileReconciler(element).addElement(
					(IASTFunctionDefinition) element,
					(org.eclipse.cdt.core.model.IFunction) coreElement);
			break;
		case ICElement.C_TYPEDEF:
			// all typedef declarations
			IASTSimpleDeclaration simpleDeclaration = BindingResolver
					.resolveBindingIASTypeDefDeclaration(elements, coreElement);
			if (simpleDeclaration.getDeclSpecifier() instanceof IASTEnumerationSpecifier) {
				event = getFileReconciler(simpleDeclaration)
						.addElement(
								(IASTEnumerationSpecifier) simpleDeclaration
										.getDeclSpecifier(),
								(ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclSpecifier() instanceof IASTCompositeTypeSpecifier) {
				IASTCompositeTypeSpecifier compositeTypeSpec = (IASTCompositeTypeSpecifier) simpleDeclaration
						.getDeclSpecifier();
				event = getFileReconciler(simpleDeclaration).addElement(
						compositeTypeSpec, (ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclSpecifier() instanceof IASTElaboratedTypeSpecifier) {
				IASTElaboratedTypeSpecifier compositeTypeSpec = (IASTElaboratedTypeSpecifier) simpleDeclaration
						.getDeclSpecifier();
				event = getFileReconciler(simpleDeclaration).addElement(
						compositeTypeSpec, (ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclSpecifier() instanceof IASTNamedTypeSpecifier) {
				event = getFileReconciler(simpleDeclaration).addElement(
						simpleDeclaration.getDeclarators()[0],
						(ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclSpecifier() instanceof IASTSimpleDeclSpecifier) {
				event = getFileReconciler(simpleDeclaration).addElement(
						simpleDeclaration.getDeclarators()[0],
						(ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclarators()[0] instanceof IASTFunctionDeclarator) {
				event = getFileReconciler(simpleDeclaration)
						.addElement(
								((IASTFunctionDeclarator) (simpleDeclaration
										.getDeclarators()[0])),
								(ITypeDef) coreElement);
			} else {
				Activator
						.log("CASTReconciler.addedElement() - ignored TYPEDEF Declaration : "
								+ simpleDeclaration.toString(), IStatus.WARNING);
			}
			break;
		case ICElement.C_UNKNOWN_DECLARATION:
			break;
		case ICElement.C_STRUCT: {
			// No differentiation between typedef struct and struct
			simpleDeclaration = BindingResolver.resolveBindingIASTStructure(
					elements, coreElement, tu);
			IASTCompositeTypeSpecifier specifier = (IASTCompositeTypeSpecifier) simpleDeclaration
					.getDeclSpecifier();
			event = getFileReconciler(newTranslationUnit).addElement(specifier,
					(IStructure) coreElement);
			break;
		}
		default:
			Activator.log("CASTReconciler.addedElement() - ignored ICElement: "
					+ coreElement.toString(), IStatus.WARNING);
			break;

		}

		notifyListeners(event, true);
	}

	/**
	 * @see org.eclipse.umlgen.reverse.c.reconciler.AbstractReconciler#removedElement(org.eclipse.cdt.core.dom.ast.IASTTranslationUnit,
	 *      org.eclipse.cdt.core.dom.ast.IASTTranslationUnit,
	 *      org.eclipse.cdt.core.model.ITranslationUnit,
	 *      org.eclipse.cdt.core.model.ICElement)
	 */
	@Override
	public void removedElement(IASTTranslationUnit originalTranslationUnit,
			IASTTranslationUnit newTranslationUnit, ITranslationUnit tu,
			ICElement coreElement) throws CoreException {
		Collection<IASTDeclaration> elements = getRemovedASTDeclaration(
				originalTranslationUnit, newTranslationUnit);
		CModelChangedEvent event = null;
		IASTDeclaration element = null;

		switch (coreElement.getElementType()) {
		case ICElement.C_VARIABLE_DECLARATION:
			element = BindingResolver.resolveBindingIASTSimpleDeclaration(
					elements, coreElement);
			event = getFileReconciler(element).removeElement(
					(IASTSimpleDeclaration) element,
					(IVariableDeclaration) coreElement);
			break;
		case ICElement.C_VARIABLE:
			element = BindingResolver.resolveBindingIASTSimpleDeclaration(
					elements, coreElement);
			event = getFileReconciler(element).removeElement(
					(IASTSimpleDeclaration) element,
					(IVariableDeclaration) coreElement);
			break;
		case ICElement.C_ENUMERATION:
			element = BindingResolver.resolveBindingIASTEnumeration(elements,
					coreElement, tu);
			event = getFileReconciler(newTranslationUnit)
					.removeElement(
							(IASTEnumerationSpecifier) (((IASTSimpleDeclaration) element)
									.getDeclSpecifier()),
							(IEnumeration) coreElement);
			break;
		case ICElement.C_FUNCTION_DECLARATION:
			// without body : operation
			element = BindingResolver.resolveBindingIASTFunctionDeclarator(
					elements, coreElement);
			event = getFileReconciler(element)
					.removeElement(
							((IASTFunctionDeclarator) ((IASTSimpleDeclaration) element)
									.getDeclarators()[0]),
							(org.eclipse.cdt.core.model.IFunctionDeclaration) coreElement);
			break;
		case ICElement.C_FUNCTION:
			// body : -> OpaqueBehavior
			element = BindingResolver.resolveBindingIASTFunctionDefinition(
					elements, coreElement);
			event = getFileReconciler(element).removeElement(
					(IASTFunctionDefinition) element,
					(org.eclipse.cdt.core.model.IFunction) coreElement);
			break;
		case ICElement.C_TYPEDEF:
			// all typedef declarations
			IASTSimpleDeclaration simpleDeclaration = BindingResolver
					.resolveBindingIASTypeDefDeclaration(elements, coreElement);
			if (simpleDeclaration.getDeclSpecifier() instanceof IASTEnumerationSpecifier) {
				event = getFileReconciler(newTranslationUnit)
						.removeElement(
								(IASTEnumerationSpecifier) simpleDeclaration
										.getDeclSpecifier(),
								(ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclSpecifier() instanceof IASTCompositeTypeSpecifier) {
				event = getFileReconciler(newTranslationUnit)
						.removeElement(
								(IASTCompositeTypeSpecifier) simpleDeclaration
										.getDeclSpecifier(),
								(ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclSpecifier() instanceof IASTElaboratedTypeSpecifier) {
				event = getFileReconciler(newTranslationUnit)
						.removeElement(
								(IASTElaboratedTypeSpecifier) simpleDeclaration
										.getDeclSpecifier(),
								(ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclSpecifier() instanceof IASTNamedTypeSpecifier) {
				event = getFileReconciler(newTranslationUnit).removeElement(
						simpleDeclaration.getDeclarators()[0],
						(ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclSpecifier() instanceof IASTSimpleDeclSpecifier) {
				event = getFileReconciler(newTranslationUnit).removeElement(
						simpleDeclaration.getDeclarators()[0],
						(ITypeDef) coreElement);
			} else if (simpleDeclaration.getDeclarators()[0] instanceof IASTFunctionDeclarator) {
				event = getFileReconciler(newTranslationUnit)
						.removeElement(
								((IASTFunctionDeclarator) (simpleDeclaration
										.getDeclarators()[0])),
								(ITypeDef) coreElement);
			}
			break;
		case ICElement.C_STRUCT:
			// No differentiation between typedef struct and struct
			element = BindingResolver.resolveBindingIASTStructure(elements,
					coreElement, tu);
			event = getFileReconciler(newTranslationUnit)
					.removeElement(
							(IASTCompositeTypeSpecifier) ((IASTSimpleDeclaration) element)
									.getDeclSpecifier(),
							(IStructure) coreElement);
			break;
		default:
			break;
		}
		notifyListeners(event, true);
	}

	public void reconcile(ITranslationUnit translationUnit,
			IWorkingCopy workingCopy) {
		try {
			IASTTranslationUnit translationUnitAST = translationUnit.getAST();
			IASTTranslationUnit workingCopyAST = workingCopy.getAST();

			reconcileFunctions(translationUnitAST, workingCopyAST, workingCopy);

			reconcilePreprocessorStatements(translationUnit,
					translationUnitAST, workingCopy, workingCopyAST);

			reconcilePreprocessorMacroDefinition(translationUnit,
					translationUnitAST, workingCopy, workingCopyAST);

			reconcileComments(translationUnit, workingCopy);
		} catch (CoreException e) {
			Activator
					.log("CASTReconciler.reconcile() - error while getting AST of the working copy",
							IStatus.ERROR, e);
		}
	}

	protected void reconcileFunctions(IASTTranslationUnit translationUnitAST,
			IASTTranslationUnit workingCopyAST, IWorkingCopy workingCopy) {
		IASTDeclaration[] originalDecls = translationUnitAST
				.getDeclarations(includeInactiveNodes);
		IASTDeclaration[] newDecls = workingCopyAST
				.getDeclarations(includeInactiveNodes);

		// Renaming of elements : in this case Function Definition.
		for (int i = 0, j = 0; i < originalDecls.length && j < newDecls.length; i++, j++) {
			if (originalDecls[i] instanceof IASTFunctionDefinition
					&& newDecls[j] instanceof IASTFunctionDefinition) {
				IASTFunctionDefinition originalFunctionDef = (IASTFunctionDefinition) originalDecls[i];
				IASTFunctionDefinition newFunctionDef = (IASTFunctionDefinition) newDecls[j];

				String originalFunctionName = originalFunctionDef
						.getDeclarator().getName().toString();
				String newFunctionName = newFunctionDef.getDeclarator()
						.getName().toString();

				if (originalFunctionName.equals(newFunctionName)) {
					reconcileTheSameFunction(workingCopy, originalFunctionDef,
							newFunctionDef);
				}
			}
		}
	}

	public void reconcileComments(ITranslationUnit originalUnit,
			IWorkingCopy newUnit) throws CoreException {
		ASTCommentReconciler commentReconciler = new ASTCommentReconciler();
		commentReconciler.addAllModelChangeListeners(getModelChangeListeners());
		commentReconciler.reconcile(originalUnit, newUnit);
	}

	public void reconcilePreprocessorMacroDefinition(
			ITranslationUnit translationUnit,
			IASTTranslationUnit translationUnitAST, IWorkingCopy workingCopy,
			IASTTranslationUnit workingCopyAST) {
		CModelChangedEvent event = null;

		Collection<IASTPreprocessorMacroDefinition> allMacroAdded = findPreProcessingMacroAdded(
				translationUnitAST, workingCopyAST);
		for (IASTPreprocessorMacroDefinition macroAdded : allMacroAdded) {
			// The added statement is in the new unit : so it is an addition
			if (macroAdded instanceof IASTPreprocessorMacroDefinition) {
				event = getFileReconciler(workingCopyAST).addElement(
						macroAdded, workingCopy);
				notifyListeners(event, true);
			}
		}

		// The added statement is in the old unit : so it is a removal
		Collection<IASTPreprocessorMacroDefinition> allMacroRemoved = findPreProcessingMacroAdded(
				workingCopyAST, translationUnitAST);
		for (IASTPreprocessorMacroDefinition macroRemoved : allMacroRemoved) {
			if (macroRemoved instanceof IASTPreprocessorMacroDefinition) {
				// IMacro iMacro = (IMacro)
				// getICDeclarationOf(macroRemoved.getName(), originalUnit,
				// ICElement.C_MACRO);
				event = getFileReconciler(workingCopyAST).removeElement(
						macroRemoved, translationUnit);
				notifyListeners(event, true);
			}
		}
	}

	public void reconcilePreprocessorStatements(
			ITranslationUnit translationUnit,
			IASTTranslationUnit translationUnitAST, IWorkingCopy workingCopy,
			IASTTranslationUnit workingCopyAST) {
		// The added statement is in the new unit : so it is an addition
		for (IASTPreprocessorStatement statementAdded : findPreProcessingStatementAdded(
				translationUnitAST, workingCopyAST)) {
			if (statementAdded instanceof IASTPreprocessorIfndefStatement) {
				if (firstIfndefMacro(statementAdded, workingCopyAST)) {
					CModelChangedEvent event = getFileReconciler(workingCopyAST)
							.addElement(
									(IASTPreprocessorIfndefStatement) statementAdded,
									workingCopy);
					notifyListeners(event, true);
				}
			}
		}

		// The added statement is in the old unit : so it is a removal
		for (IASTPreprocessorStatement statementAdded : findPreProcessingStatementAdded(
				workingCopyAST, translationUnitAST)) {
			if (statementAdded instanceof IASTPreprocessorIfndefStatement) {
				if (firstIfndefMacro(statementAdded, translationUnitAST)) {
					CModelChangedEvent event = getFileReconciler(workingCopyAST)
							.removeElement(
									(IASTPreprocessorIfndefStatement) statementAdded,
									translationUnit);
					notifyListeners(event, true);
				}
			}
		}
	}

	private void reconcileTheSameFunction(ITranslationUnit newUnit,
			IASTFunctionDefinition originalFunctionDefinition,
			IASTFunctionDefinition newFunctionDefinition) {
		IASTFunctionDeclarator originalOutermostDeclarator = (IASTFunctionDeclarator) ASTQueries
				.findOutermostDeclarator(originalFunctionDefinition
						.getDeclarator());
		IASTFunctionDeclarator newOutermostDeclarator = (IASTFunctionDeclarator) ASTQueries
				.findOutermostDeclarator(newFunctionDefinition.getDeclarator());

		originalOutermostDeclarator.getName().resolveBinding();
		newOutermostDeclarator.getName().resolveBinding();

		IFunction newFunction = (IFunction) newOutermostDeclarator.getName()
				.getBinding();

		List<FunctionParameter> parameters = ASTUtil
				.collectParameterInformation(newFunctionDefinition
						.getDeclarator());

		CModelChangedEvent event = FunctionParameterChanged.builder()
				.functionName(newFunction.getName()).setParameters(parameters)
				.translationUnit(newUnit).build();
		notifyListeners(event, true);

		String newbody = newFunctionDefinition.getBody().getRawSignature();
		String oldBody = originalFunctionDefinition.getBody().getRawSignature();
		String currentName = newFunctionDefinition.getDeclarator().getName()
				.toString();
		event = FunctionBodyChanged.builder().setBody(newbody)
				.setOldBody(oldBody).currentName(currentName)
				.translationUnit(newUnit).build();
		notifyListeners(event, true);

	}
}
