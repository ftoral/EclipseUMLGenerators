/**
 * C/model/sync/providers/Class3_monPort1_ProviderSync.java
 *
 * File generated from the ::model::Class3::monPort1 uml Port
 */
package C.model.sync.providers;

import model.InterfaceSynchronous;
import org.eclipse.umlgen.rtsj.framework.*;
import org.eclipse.umlgen.rtsj.framework.types.*;
import org.eclipse.umlgen.rtsj.framework.sync.PortProviderSync;
import model.params.*;

public class Class3_monPort1_ProviderSync implements PortProviderSync {

	InterfaceSynchronous provider;
	String ident;
	
	CommunicationLayer communicationLayer;

	public Class3_monPort1_ProviderSync(InterfaceSynchronous provider, String ident, CommunicationLayer communicationLayer) {
		this.provider = provider;
		this.ident = ident;
		communicationLayer.registerSynchronousProviderPort(ident, this);
		this.communicationLayer = communicationLayer;
	}

	public void monService1(InterfaceSynchronous_monService1_Int_Params param) {
		provider.monService1(param.param1);
	}		
	public void monService2(InterfaceSynchronous_monService2_Params param) {
		provider.monService2(param.param);
	}		
	public void monService1(InterfaceSynchronous_monService1_BooleanInt_Params param) {
		provider.monService1(param.param1, param.param2);
	}		
	public Object invoke(String op, ParameterSet param) throws ServiceNotFoundException {
		if (op.equals("monService1int")) {
			this.monService1((InterfaceSynchronous_monService1_Int_Params) param);
		} else if (op.equals("monService2SharedData")) {
			this.monService2((InterfaceSynchronous_monService2_Params) param);
		} else if (op.equals("monService1int_boolean")) {
			this.monService1((InterfaceSynchronous_monService1_BooleanInt_Params) param);
		} else {
			throw new ServiceNotFoundException();
		}
		return null;
	}
}