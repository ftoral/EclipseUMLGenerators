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
package org.eclipse.umlgen.dsl.eth;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.umlgen.dsl.asl.configurations.ProtocolCommunication;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ethernet Conf</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.umlgen.dsl.eth.EthernetConf#getConnectors <em>Connectors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.umlgen.dsl.eth.EthPackage#getEthernetConf()
 * @model
 * @generated
 */
public interface EthernetConf extends ProtocolCommunication {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2012 Obeo, CNES\r\nAll rights reserved.  This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 1.0.\r\nYou can apply any license to the files generated with this template\r\nand Acceleo.\r\n\r\nOriginal contributors : Obeo\r\nContributors : \r\nObeo - Cedric Notot";

	/**
	 * Returns the value of the '<em><b>Connectors</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Connector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connectors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connectors</em>' reference list.
	 * @see org.eclipse.umlgen.dsl.eth.EthPackage#getEthernetConf_Connectors()
	 * @model
	 * @generated
	 */
	EList<Connector> getConnectors();

} // EthernetConf
