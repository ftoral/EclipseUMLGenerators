package org.eclipse.umlgen.reverse.c.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class OrderedXMIResourceImpl extends XMIResourceImpl {

	public OrderedXMIResourceImpl(URI uri) {
		super(uri);
	}

	@Override
	protected XMLSave createXMLSave() {
		return new OrderedXMISaveImpl(createXMLHelper());
	}
}
