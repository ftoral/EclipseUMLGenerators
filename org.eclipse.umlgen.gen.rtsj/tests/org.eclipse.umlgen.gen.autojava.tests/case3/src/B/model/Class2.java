/**
 * model/Class2.java
 *
 * File generated from the  uml Class
 */
package B.model;
import B.model.*;
import org.eclipse.umlgen.rtsj.annotations.*;


import org.eclipse.umlgen.rtsj.framework.*;
import org.eclipse.umlgen.rtsj.framework.types.*;
import B.model.async.users.Class2_monPort2_UserAsync;
import model.*;

// Start of user code to add imports for Class2

// End of user code

/**
 * Description of the class Class2.
 *
 */

@Sporadic(period = 1000, phase = 0, priority = 29, deadline = 0, wcet = 0, bcet = 0, messages = 10)
public class Class2   {
	
	
	
	// Start of user code to add fields for Class2
	
	// End of user code
	
	public void readObject(ArgsBuffer argsBuffer) {
	}
	
	public void writeObject(ArgsBuffer argsBuffer) {
	}
	
	
	
	
	
	/**
	 * Constructor.
	 */
	public Class2() {
		super();
		// Start of user code for constructor Class2
		// End of user code
	}
	PortBuffer pbuffer = new PortBuffer(0);
	
	@ignore
	public void before() {
		// Start of user code for method Class2.before():
		
		// End of user code 
	}
	
	@ignore
	public void after() {
		// Start of user code for method Class2.after():
		
		// End of user code 
	}
	
	@ignore
	private final Object thread = new Object();
	
	
	public int getThreadPeriod() { return 1000; }
	public int getThreadPhase() { return 0; }
	public Object getThread() { return thread; }
	public int getThreadPriority() { return 29; }
	public PortBuffer getPbuffer() { return pbuffer; }
	
	
	
	@ignore
	public void start_all() {
		init();
	}
	
	@ignore
	public void body() {
		before();
		after();
	}
	
	@ignore
	public void init() {
		// Start of user code for method Class2.init():
	
		// End of user code 
	}
	
	
	/**  CONNECTORS.  ***/
	protected Class2_monPort2_UserAsync monPort2;
	
	public void initPortsGenerator(String componentInstance, CommunicationLayer communicationLayer) {
		monPort2 = new Class2_monPort2_UserAsync(componentInstance + ":monPort2", componentInstance, communicationLayer);
	}
	
	
	
}