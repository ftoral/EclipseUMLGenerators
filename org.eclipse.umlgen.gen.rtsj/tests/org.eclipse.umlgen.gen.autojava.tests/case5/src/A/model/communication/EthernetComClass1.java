/**
 * model/Class1.java
 *
 * File generated from the  uml Class
 */
package A.model.communication;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.*;
import fwk.CommunicationLayer;
import fwk.Ethernet.ComProtocol;
import fwk.Ethernet.ServerThread;

import fwk.ArgsBuffer;

public class EthernetComClass1 implements ComProtocol {


	static final int serverPort = 27;
	ArgsBuffer header = new ArgsBuffer(19);
	
	public EthernetComClass1(CommunicationLayer communicationLayer) {
		new ServerThread(serverPort, communicationLayer) ;
	}
	
	public void sendFrame (String dest, String ident, String service, ArgsBuffer params, int priority){
		header.writeString(ident);
		header.writeString(service);
		header.writeInteger(priority);
		if (params != null) {		
			header.writeInteger(params.getUsed());
		} else {
			header.writeInteger(0);
		}
		int bufferSize;
	}
}