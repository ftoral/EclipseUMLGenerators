package org.eclipse.umlgen.reverse.c.resource;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;

public class OrderedXMISaveImpl extends XMISaveImpl {
	public OrderedXMISaveImpl(XMLHelper helper) {
		super(helper);
	}

	public OrderedXMISaveImpl(Map<?, ?> options, XMLHelper helper,
			String encoding) {
		super(options, helper, encoding, "1.0");
	}

	public OrderedXMISaveImpl(Map<?, ?> options, XMLHelper helper,
			String encoding, String xmlVersion) {
		super(options, helper, encoding, xmlVersion);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void saveContainedMany(EObject o, EStructuralFeature f) {

		EList<EObject> valeurs = (EList<EObject>) helper.getValue(o, f);
		ECollections.sort(valeurs, new EObjectComparator());
		for (Iterator<EObject> it = valeurs.iterator(); it.hasNext();) {
			EObject valeur = it.next();
			if (valeur != null) {
				saveElement(valeur, f);
			}
		}
	}
}
