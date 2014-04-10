/**
 * model/SharedData.java
 *
 * File generated from the  uml Class
 */
package model;
import B.model.*;
import org.eclipse.umlgen.rtsj.annotations.*;


import org.eclipse.umlgen.rtsj.framework.*;
import org.eclipse.umlgen.rtsj.framework.types.*;
import model.*;

// Start of user code to add imports for SharedData

// End of user code

/**
 * Description of the class SharedData.
 *
 */

public class SharedData   {
	
	
	
	public int[] data1 = new int[5];
	public byte data2 = -1;
	// Start of user code to add fields for SharedData
	
	// End of user code
	
	public void readObject(ArgsBuffer argsBuffer) {
		for (int i = 0; i < data1.length; i++) {
			data1[i] = argsBuffer.readInteger();
		}
		data2 = argsBuffer.readByte();
	}
	
	public void writeObject(ArgsBuffer argsBuffer) {
		for (int i = 0; i < data1.length; i++) {
			argsBuffer.writeInteger(data1[i]);
		}
		argsBuffer.writeByte(data2);
	}
	
	
	
	
	
	/**
	 * Constructor.
	 */
	public SharedData() {
		super();
		// Start of user code for constructor SharedData
		// End of user code
	}
	
	
	/**  CONNECTORS.  ***/
	
	
	
	
}