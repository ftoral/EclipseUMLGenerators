/**
 * model/InterfaceAsynchronous.java
 *
 * File generated from the  uml Interface
 */
package model.params;

import fwk.ArgsBuffer;
import fwk.ParameterSet;

public class InterfaceAsynchronous_monService1_Params implements ParameterSet {
	public int param1;
	public int param2;
	
	public InterfaceAsynchronous_monService1_Params(boolean provider) {
		if (provider) {
		}
	}

	public void readObject(ArgsBuffer argsBuffer) {
		param1 = argsBuffer.readInteger();
		param2 = argsBuffer.readInteger();
	}

	public void writeObject(ArgsBuffer argsBuffer) {
		argsBuffer.writeInteger(param1);
		argsBuffer.writeInteger(param2);
	}

	public int byteSize() {
		return 8;
	}
}