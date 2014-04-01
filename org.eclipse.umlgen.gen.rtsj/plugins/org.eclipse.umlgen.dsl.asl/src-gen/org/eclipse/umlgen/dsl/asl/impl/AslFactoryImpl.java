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
package org.eclipse.umlgen.dsl.asl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.umlgen.dsl.asl.*;
import org.eclipse.umlgen.dsl.asl.ArchitecturalStyle;
import org.eclipse.umlgen.dsl.asl.AslFactory;
import org.eclipse.umlgen.dsl.asl.AslPackage;
import org.eclipse.umlgen.dsl.asl.Configuration;
import org.eclipse.umlgen.dsl.asl.ConfigurationRepository;
import org.eclipse.umlgen.dsl.asl.GenericParam;
import org.eclipse.umlgen.dsl.asl.Library;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AslFactoryImpl extends EFactoryImpl implements AslFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Obeo, CNES\r\nAll rights reserved.  This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 1.0.\r\nYou can apply any license to the files generated with this template\r\nand Acceleo.\r\n\r\nOriginal contributors : Obeo\r\nContributors : \r\nObeo - Cedric Notot";

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AslFactory init() {
		try {
			AslFactory theAslFactory = (AslFactory)EPackage.Registry.INSTANCE.getEFactory(AslPackage.eNS_URI);
			if (theAslFactory != null) {
				return theAslFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AslFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AslFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AslPackage.LIBRARY: return createLibrary();
			case AslPackage.ARCHITECTURAL_STYLE: return createArchitecturalStyle();
			case AslPackage.CONFIGURATION: return createConfiguration();
			case AslPackage.GENERIC_PARAM: return createGenericParam();
			case AslPackage.CONFIGURATION_REPOSITORY: return createConfigurationRepository();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library createLibrary() {
		LibraryImpl library = new LibraryImpl();
		return library;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitecturalStyle createArchitecturalStyle() {
		ArchitecturalStyleImpl architecturalStyle = new ArchitecturalStyleImpl();
		return architecturalStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Configuration createConfiguration() {
		ConfigurationImpl configuration = new ConfigurationImpl();
		return configuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericParam createGenericParam() {
		GenericParamImpl genericParam = new GenericParamImpl();
		return genericParam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationRepository createConfigurationRepository() {
		ConfigurationRepositoryImpl configurationRepository = new ConfigurationRepositoryImpl();
		return configurationRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AslPackage getAslPackage() {
		return (AslPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AslPackage getPackage() {
		return AslPackage.eINSTANCE;
	}

} //AslFactoryImpl
