package org.eclipse.umlgen.gen.c.builder.internal;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;
import org.eclipse.umlgen.gen.c.builder.UML2CBundleConstant;

/**
 * The activator class controls the plug-in life cycle
 */
public class UML2CBuilderBundle extends Plugin {

	// The shared instance
	private static UML2CBuilderBundle plugin;

	/**
	 * The constructor
	 */
	public UML2CBuilderBundle() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Log an exception into the Eclipse log file <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param e
	 *            the exception to log
	 * @generated
	 */
	public static void log(Throwable e) {
		if (e instanceof InvocationTargetException) {
			e = ((InvocationTargetException)e).getTargetException();
		}

		IStatus status = null;
		if (e instanceof CoreException) {
			status = ((CoreException)e).getStatus();
		} else {
			status = new Status(IStatus.ERROR, getId(), IStatus.OK, "Error", e);
		}

		log(status);
	}

	/**
	 * Log a message with given level into the Eclipse log file <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param message
	 *            the message to log
	 * @param level
	 *            the message priority
	 * @generated
	 */
	public static void log(String message, int level, Throwable e) {
		IStatus status = null;
		status = new Status(level, getId(), IStatus.OK, message, e);
		log(status);
	}

	/**
	 * Log a message with given level into the Eclipse log file <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param message
	 *            the message to log
	 * @param level
	 *            the message priority
	 * @generated
	 */
	public static void log(String message, int level) {
		IStatus status = null;
		status = new Status(level, getId(), IStatus.OK, message, null);
		log(status);
	}

	/**
	 * Log an IStatus <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param status
	 *            the status to log
	 * @generated
	 */
	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the Plugin Id
	 * @generated
	 */
	public static String getId() {
		return UML2CBundleConstant.PLUGIN_ID;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static UML2CBuilderBundle getDefault() {
		return plugin;
	}

}
