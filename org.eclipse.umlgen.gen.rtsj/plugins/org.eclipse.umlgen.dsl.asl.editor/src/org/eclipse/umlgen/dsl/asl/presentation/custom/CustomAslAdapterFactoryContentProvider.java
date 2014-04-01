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
package org.eclipse.umlgen.dsl.asl.presentation.custom;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * Specific factory to rout to its own descriptor with a <code>ValuesLabelProvider</code>.
 * @author cnotot
 *
 */
public class CustomAslAdapterFactoryContentProvider extends AdapterFactoryContentProvider {

	public CustomAslAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected IPropertySource createPropertySource(Object object,
			IItemPropertySource itemPropertySource) {
		// TODO Auto-generated method stub
		return new CustomAslPropertySource(object, itemPropertySource);
	}

}
