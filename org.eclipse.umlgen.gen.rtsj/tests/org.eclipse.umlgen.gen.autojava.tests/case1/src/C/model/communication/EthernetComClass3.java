/**
 * model/Class3.java
 *
 * File generated from the  uml Class
 */
package C.model.communication;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.*;
import org.eclipse.umlgen.rtsj.framework.CommunicationLayer;
import org.eclipse.umlgen.rtsj.framework.ethernet.ComProtocol;
import org.eclipse.umlgen.rtsj.framework.ethernet.ServerThread;

import org.eclipse.umlgen.rtsj.framework.ArgsBuffer;

public class EthernetComClass3 implements ComProtocol {


	static final int serverPort = 29;
	ArgsBuffer header = new ArgsBuffer(19);
	
	public EthernetComClass3(CommunicationLayer communicationLayer) {
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