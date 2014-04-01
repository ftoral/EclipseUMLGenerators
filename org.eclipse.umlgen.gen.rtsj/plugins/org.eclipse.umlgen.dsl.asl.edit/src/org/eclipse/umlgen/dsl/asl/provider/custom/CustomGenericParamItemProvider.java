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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.umlgen.dsl.asl.provider.GenericParamItemProvider;

/**
 * Specific item provider for generic parameters in order to benefit from
 * unresolved proxies decoration.
 * 
 * @author cnotot
 * 
 */
public class CustomGenericParamItemProvider extends GenericParamItemProvider {

	private CustomDecorationItemProvider decorationDelegate;

	public CustomGenericParamItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		decorationDelegate = new CustomDecorationItemProvider(adapterFactory);
	}

	@Override
	public Object getImage(Object object) {
		return decorationDelegate.getImage(object, super.getImage(object));
	}

}
