/*******************************************************************************
 * Copyright (c) 2010, 2014 CS Systèmes d'Information (CS-SI).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Christophe Le Camus (CS-SI) - initial API and implementation
 *     Sebastien Gabel (CS-SI) - evolutions
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.event;

import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorStatement;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.umlgen.c.common.util.ModelManager;
import org.eclipse.umlgen.c.common.util.ModelUtil;

public class CommentRemoved extends CommentEvent {
	/**
	 * @see org.eclipse.umlgen.reverse.c.event.CModelChangedEvent#notifyChanges(org.eclipse.umlgen.c.common.util.ModelManager)
	 */
	@Override
	public void notifyChanges(ModelManager manager) {

		IASTNode parent = getParent();
		Classifier matchingClassifier = ModelUtil.findClassifierInPackage(manager.getSourcePackage(),
				getUnitName());

		String detailsEntry = getKey();

		Element element = null;

		if (parent instanceof IASTTranslationUnit) {
			element = matchingClassifier;
		}

		if (parent instanceof IASTFunctionDefinition) {
			if (matchingClassifier instanceof Class) {
				element = ((Class)matchingClassifier).getOwnedBehavior(((IASTFunctionDefinition)getParent())
						.getDeclarator().getName().toString(), false, UMLPackage.Literals.FUNCTION_BEHAVIOR,
						false);
			}
		}
		if (parent instanceof IASTFunctionDeclarator) {
			if (matchingClassifier instanceof Class) {
				element = ((Class)matchingClassifier).getOwnedBehavior(((IASTFunctionDeclarator)getParent())
						.getName().toString(), false, UMLPackage.Literals.OPERATION, false);
			}
		}

		if (parent instanceof IASTSimpleDeclaration) {
			String name = ((IASTSimpleDeclaration)getParent()).getDeclarators()[0].getName().toString();
			if (matchingClassifier instanceof Class) {
				element = ((Class)matchingClassifier).getFeature(name);

				if (element == null) {
					element = ((Class)matchingClassifier).getNestedClassifier(name);
				}
			}
			if (matchingClassifier instanceof Interface) {
				element = ((Interface)matchingClassifier).getFeature(name);

				if (element == null) {
					element = ((Interface)matchingClassifier).getNestedClassifier(name);
				}
			}
		}

		if (parent instanceof IASTParameterDeclaration) {
			IASTNode functionOrOperation = parent.getParent();
			IASTParameterDeclaration myParameter = (IASTParameterDeclaration)parent;
			if (functionOrOperation instanceof IASTFunctionDeclarator) {
				// this is the method
				IASTFunctionDeclarator declarator = (IASTFunctionDeclarator)functionOrOperation;
				if (declarator.getParent() instanceof IASTFunctionDefinition) {
					IASTFunctionDefinition function = (IASTFunctionDefinition)declarator.getParent();
					OpaqueBehavior fct = (OpaqueBehavior)((Class)matchingClassifier)
							.getOwnedBehavior(function.getDeclarator().getName().toString());
					// The comment may concern a function that is declared in
					// the .h but not added in this to the model
					if (fct != null) {
						for (Parameter parameter : fct.getOwnedParameters()) {
							if (parameter.getName().equals(myParameter.getDeclarator().getName().toString())) {
								element = parameter;
							}
						}
					}
				} else {
					// the declaration of an operation
					for (Element ownedElement : matchingClassifier.getOwnedElements()) {
						if (ownedElement instanceof Operation) {
							if (((Operation)ownedElement).getName().equals(declarator.getName().toString())) {
								for (Parameter parameter : ((Operation)ownedElement).getOwnedParameters()) {
									if (parameter.getName().equals(
											myParameter.getDeclarator().getName().toString())) {
										element = parameter;
									}
								}
							}
						}
					}
				}
			}
		}

		if (parent instanceof IASTPreprocessorStatement) {
			if (parent instanceof IASTPreprocessorIncludeStatement) {
				String elementName = ((IASTPreprocessorIncludeStatement)parent).getName().toString();
				elementName = elementName.substring(0, elementName.length() - 2);
				EList<DirectedRelationship> list = matchingClassifier.getSourceDirectedRelationships();
				for (DirectedRelationship usage : list) {
					for (Element anElement : usage.getTargets()) {
						if (anElement instanceof Classifier) {
							if (((Classifier)anElement).getName().compareTo(elementName) == 0) {
								element = usage;
							}
						}
					}

				}
			}
			if (parent instanceof IASTPreprocessorMacroDefinition) {
				String elementName = ((IASTPreprocessorMacroDefinition)parent).getName().toString();
				element = matchingClassifier.getFeature(elementName);
			}

		}

		if (element != null) {
			// FIXME MIGRATION reference to modeler
			// EAnnotation annotation =
			// element.getEAnnotation(IAnnotationConstants.DOCUMENTATION_SOURCE);
			// if (annotation != null)
			// {
			// if (annotation.getDetails().get(detailsEntry) != null)
			// {
			// annotation.getDetails().removeKey(detailsEntry);
			// }
			// }
		}
	}

	/**
	 * Gets the right builder
	 *
	 * @return the builder for this event
	 */
	public static Builder<CommentRemoved> builder() {
		return new Builder<CommentRemoved>() {
			private CommentRemoved event = new CommentRemoved();

			/**
			 * @see org.eclipse.umlgen.reverse.c.event.CommentEvent.Builder#getEvent()
			 */
			@Override
			protected CommentRemoved getEvent() {
				return event;
			}
		};
	}
}
