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
package org.eclipse.umlgen.dsl.asl.presentation.elements;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.umlgen.dsl.asl.presentation.ValuesLabelProvider;

/**
 * Specific descriptor with a <code>ValuesLabelProvider</code>.
 * @author cnotot
 *
 */
public class ElementsPropertyDescriptor extends PropertyDescriptor {

	public ElementsPropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor) {
		super(object, itemPropertyDescriptor);
	}
	
	@Override
	public ILabelProvider getLabelProvider() {
		return new ValuesLabelProvider((LabelProvider)super.getLabelProvider());
	}
	
}
