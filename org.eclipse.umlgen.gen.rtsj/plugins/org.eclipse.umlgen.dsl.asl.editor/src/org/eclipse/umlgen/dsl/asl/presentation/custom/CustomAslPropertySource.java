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

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.umlgen.dsl.asl.AslPackage;
import org.eclipse.umlgen.dsl.asl.presentation.elements.ElementsPropertyDescriptor;

/**
 * Specific property source to rout to its own descriptor with a <code>ValuesLabelProvider</code>.
 * @author cnotot
 *
 */
public class CustomAslPropertySource extends PropertySource {

	public CustomAslPropertySource(Object object,
			IItemPropertySource itemPropertySource) {
		super(object, itemPropertySource);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected IPropertyDescriptor createPropertyDescriptor(
			IItemPropertyDescriptor itemPropertyDescriptor) {
		if (itemPropertyDescriptor.getFeature(null).equals(AslPackage.Literals.GENERIC_PARAM__REFERENCES)){
			return new ElementsPropertyDescriptor(object, itemPropertyDescriptor);
		}
		return super.createPropertyDescriptor(itemPropertyDescriptor);
	}

}
