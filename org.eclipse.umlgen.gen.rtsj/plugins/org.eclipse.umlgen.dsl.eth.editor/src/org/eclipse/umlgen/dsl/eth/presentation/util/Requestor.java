package org.eclipse.umlgen.dsl.eth.presentation.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Port;

public class Requestor {
	
	public static ConnectorEnd getStart(Connector connector) {
		for (ConnectorEnd end : connector.getEnds()) {
			EObject obj = end.getRole();
			if(obj instanceof Port) {
				Port port = (Port) obj;
				if (port.getType() == null) {
					return end;
				}
			}
		};
		return null;
	}
	
	public static ConnectorEnd getEnd(Connector connector) {
		for (ConnectorEnd end : connector.getEnds()) {
			EObject obj = end.getRole();
			if(obj instanceof Port) {
				Port port = (Port) obj;
				if (port.getType() != null) {
					return end;
				}
			}
		};
		return null;
	}
	
	public static Set<Connector> getConnectors(final Port port) {
		final Set<Connector> connectors = new HashSet<Connector>();
		for (ConnectorEnd connectorEnd : port.getEnds()) {
			connectors.add((Connector) connectorEnd.eContainer());
		}
		return connectors;
	}
}
