/**
 * Copyright (c) 2012 Obeo, CNES
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 1.0.
 * You can apply any license to the files generated with this template
 * and Acceleo.
 * 
 * Original contributors : Obeo
 * Contributors : 
 * Obeo - Cedric Notot
 */
package org.eclipse.umlgen.dsl.asl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Architectural Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.umlgen.dsl.asl.ArchitecturalStyle#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.umlgen.dsl.asl.ArchitecturalStyle#getConfigurations <em>Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.umlgen.dsl.asl.AslPackage#getArchitecturalStyle()
 * @model
 * @generated
 */
public interface ArchitecturalStyle extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2012 Obeo, CNES\r\nAll rights reserved.  This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 1.0.\r\nYou can apply any license to the files generated with this template\r\nand Acceleo.\r\n\r\nOriginal contributors : Obeo\r\nContributors : \r\nObeo - Cedric Notot";

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.umlgen.dsl.asl.AslPackage#getArchitecturalStyle_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.umlgen.dsl.asl.ArchitecturalStyle#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Configurations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.umlgen.dsl.asl.Configuration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configurations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configurations</em>' reference list.
	 * @see org.eclipse.umlgen.dsl.asl.AslPackage#getArchitecturalStyle_Configurations()
	 * @model
	 * @generated
	 */
	EList<Configuration> getConfigurations();

} // ArchitecturalStyle
