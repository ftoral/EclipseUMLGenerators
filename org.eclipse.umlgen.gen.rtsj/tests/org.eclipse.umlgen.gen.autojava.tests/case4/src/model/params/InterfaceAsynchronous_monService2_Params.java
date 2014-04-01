/**
 * model/InterfaceAsynchronous.java
 *
 * File generated from the  uml Interface
 */
package model.params;

import fwk.ArgsBuffer;
import model.SharedData;
import fwk.ParameterSet;

public class InterfaceAsynchronous_monService2_Params implements ParameterSet {
	public SharedData param1;
	
	public InterfaceAsynchronous_monService2_Params(boolean provider) {
		if (provider) {
			param1 = new SharedData();
		}
	}

	public void readObject(ArgsBuffer argsBuffer) {
		param1.readObject(argsBuffer);
	}

	public void writeObject(ArgsBuffer argsBuffer) {
		param1.writeObject(argsBuffer);
	}

	public int byteSize() {
		return 21;
	}
}