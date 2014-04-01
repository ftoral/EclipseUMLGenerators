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
package org.eclipse.umlgen.dsl.asl.provider.custom;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.umlgen.dsl.asl.provider.AslItemProviderAdapterFactory;

/**
 * Specific item provider adapter factory to manage decorators in case of
 * unresolved proxies.
 * 
 * @author cnotot
 * 
 */
public class CustomAslItemProviderAdapterFactory extends
		AslItemProviderAdapterFactory {

	@Override
	public Adapter createGenericParamAdapter() {
		if (genericParamItemProvider == null) {
			genericParamItemProvider = new CustomGenericParamItemProvider(this);
		}
		return genericParamItemProvider;
	}

}
