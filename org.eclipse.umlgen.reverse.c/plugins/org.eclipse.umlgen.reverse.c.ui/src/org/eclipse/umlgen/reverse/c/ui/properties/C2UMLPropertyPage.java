/*******************************************************************************
 * Copyright (c) 2010, 2014 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sebastien Gabel (CS) - initial API and implementation
 *******************************************************************************/
package org.eclipse.umlgen.reverse.c.ui.properties;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.eclipse.umlgen.gen.c.common.BundleConstants;
import org.eclipse.umlgen.gen.c.common.PreferenceStoreManager;
import org.eclipse.umlgen.reverse.c.ui.internal.bundle.Messages;

/**
 * Manages the customization for reverse C to UML.<br />
 * Two categories are suggested : the first one allows to specify UML/UMLDI resources to synchronize, the
 * second one enables to declare the different UML packages on which classes will be placed.<br>
 * Creation : 04 may 2010<br/>
 *
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
// FIXME MIGRATION reference to facilities
public class C2UMLPropertyPage extends AbstractPreferencePage {
	private RadioGroupFieldEditor syncModeEditor;

	// FIXME MIGRATION reference to facilities
	// private ResourceFieldEditor diagramPath;

	private StringFieldEditor modelPath;

	// FIXME MIGRATION reference to facilities
	// private EObjectFieldEditor srcPath;

	// FIXME MIGRATION reference to facilities
	// private EObjectFieldEditor typePath;

	// FIXME MIGRATION reference to facilities
	// private EObjectFieldEditor extPath;

	private ResourceSet rscSet;

	private Object[] packages;

	private ILabelProvider defaultLabelProvider;

	private ILabelProvider advancedLabelProvider;

	/**
	 * Constructor
	 */
	public C2UMLPropertyPage() {
		rscSet = new ResourceSetImpl();

		// FIXME MIGRATION reference to modeler
		// advancedLabelProvider = new
		// QualifiedNameLabelProvider().createAdapterFactory();

		defaultLabelProvider = new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}

	@Override
	public void setElement(IAdaptable element) {
		super.setElement(element);
		PreferenceStoreManager.setDefaultValues((IProject)getElement());
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		// Main Composite
		final Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		final GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		mainComposite.setLayout(layout);

		// create the top group related to define the synchronization mode when
		// a new project has been started
		createSyncModeGroup(mainComposite);

		// create the second group related to model paths
		createModelsGroup(mainComposite);

		// create the bottom group related to project settings
		createSettingsGroup(mainComposite);

		loadPreferences();

		return mainComposite;
	}

	/**
	 * Creates the first group on which synchronization policy is defined (from C source or from UML model).
	 *
	 * @param parent
	 *            The composite parent
	 */
	private void createSyncModeGroup(Composite parent) {
		String[][] data = new String[2][2];
		data[0] = new String[] {Messages.getString("C2UMLPropertyPage.4"), BundleConstants.SYNC_SOURCE_VALUE }; //$NON-NLS-1$
		data[1] = new String[] {Messages.getString("C2UMLPropertyPage.5"), BundleConstants.SYNC_MODEL_VALUE }; //$NON-NLS-1$
		syncModeEditor = new RadioGroupFieldEditor(BundleConstants.SYNC_AT_STARTING, Messages
				.getString("C2UMLPropertyPage.6"), 2, data, parent, true); //$NON-NLS-1$
		syncModeEditor.setPreferenceStore(getPreferenceStore());
	}

	/**
	 * Creates the second group on which access paths to models must be specified.
	 *
	 * @param parent
	 *            The composite parent
	 */
	private void createModelsGroup(Composite parent) {
		final Group mainGroup = new Group(parent, SWT.NONE);
		mainGroup.setText(Messages.getString("C2UMLPropertyPage.0")); //$NON-NLS-1$
		mainGroup.setLayout(new GridLayout());
		mainGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		// Composite for the diagram path
		final Composite diagramComposite = new Composite(mainGroup, SWT.NONE);
		diagramComposite.setLayout(new GridLayout());
		diagramComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 0, 0));

		// FIXME MIGRATION reference to facilities
		// Access to UMLDI Model path
		//        diagramPath = new ResourceFieldEditor(BundleConstants.UMLDI_MODEL_PATH, Messages.getString("C2UMLPropertyPage.1"), diagramComposite); //$NON-NLS-1$
		// diagramPath.setPreferenceStore(getPreferenceStore());
		// diagramPath.setPage(this);
		// diagramPath.setEmptyStringAllowed(false);
		// diagramPath.setPropertyChangeListener(new IPropertyChangeListener()
		// {
		// /**
		// * @see
		// org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
		// */
		// public void propertyChange(PropertyChangeEvent event)
		// {
		// if (FieldEditor.VALUE.equals(event.getProperty()))
		// {
		// // deduce and set a default value into the next field.
		// URI uri = URI.createURI(diagramPath.getStringValue(), false);
		// try
		// {
		// Resource rsc = rscSet.getResource(uri, true);
		// EObject root = rsc.getContents().get(0);
		// // FIXME MIGRATION reference to modeler
		// // if (root instanceof Diagrams)
		// // {
		// // EObject model = ((Diagrams) root).getModel();
		// // URI modelURI = EcoreUtil.getURI(model);
		// //
		// modelPath.setStringValue(URI.decode(modelURI.trimFragment().toString()));
		// // }
		// }
		// catch (Exception e)
		// {
		//                        modelPath.setStringValue(""); //$NON-NLS-1$
		// }
		// }
		// }
		// });

		// Composite for the model path
		final Composite modelComposite = new Composite(mainGroup, SWT.NONE);
		modelComposite.setLayout(new GridLayout());
		modelComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 0, 0));

		// Access to UML Model path
		modelPath = new StringFieldEditor(BundleConstants.UML_MODEL_PATH, Messages
				.getString("C2UMLPropertyPage.3"), modelComposite); //$NON-NLS-1$
		modelPath.setEnabled(false, modelComposite);
		modelPath.getLabelControl(modelComposite).setEnabled(true);
		modelPath.setPreferenceStore(getPreferenceStore());
		modelPath.setPage(this);
		modelPath.setEmptyStringAllowed(false);
		modelPath.setPropertyChangeListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (FieldEditor.VALUE.equals(event.getProperty())) {
					resetPathFields();
					extractPackagesFromModel();
				}
			}
		});
	}

	/**
	 * Resets the three bottom fields when the value of the UML model field changes.
	 */
	private void resetPathFields() {
		srcPath.setStringValue(""); //$NON-NLS-1$
		typePath.setStringValue(""); //$NON-NLS-1$
		extPath.setStringValue(""); //$NON-NLS-1$
	}

	/**
	 * Extracts all UML Packages contained into the semantic model, then this collection is transformed into
	 * an array before being set to the different eobject field editor.
	 */
	private void extractPackagesFromModel() {
		Collection<EObject> collection = new ArrayList<EObject>();
		URI uri = URI.createURI(modelPath.getStringValue(), false);
		try {
			Resource model = rscSet.getResource(uri, true);
			if (model != null) {
				for (TreeIterator<EObject> iterator = EcoreUtil.<EObject> getAllContents(model, false); iterator
						.hasNext();) {
					collection.add(iterator.next());
				}
				packages = EcoreUtil.getObjectsByType(collection, UMLPackage.Literals.PACKAGE).toArray();
				srcPath.setCandidates(packages);
				typePath.setCandidates(packages);
				extPath.setCandidates(packages);
			}
		} catch (Exception e) {
			// nothing to do
		}
	}

	/**
	 * Creates the settings group (bottom part).
	 *
	 * @param parent
	 *            The composite parent
	 */
	private void createSettingsGroup(Composite parent) {
		// Settings Group
		final Group settingsGroup = new Group(parent, SWT.NONE);
		settingsGroup.setLayout(new GridLayout());
		settingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		settingsGroup.setText(Messages.getString("C2UMLPropertyPage.7")); //$NON-NLS-1$

		// Intermediate composite to permit to get inner borders
		final Composite intermediateComposite = new Composite(settingsGroup, SWT.NONE);
		intermediateComposite.setLayout(new GridLayout());
		intermediateComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		srcPath = createPathFieldEditor(intermediateComposite, BundleConstants.SRC_PCK_NAME, Messages
				.getString("C2UMLPropertyPage.2")); //$NON-NLS-1$
		typePath = createPathFieldEditor(intermediateComposite, BundleConstants.TYPE_PCK_NAME, Messages
				.getString("C2UMLPropertyPage.9")); //$NON-NLS-1$
		extPath = createPathFieldEditor(intermediateComposite, BundleConstants.EXT_PCK_NAME, Messages
				.getString("C2UMLPropertyPage.10")); //$NON-NLS-1$
	}

	// FIXME MIGRATION reference to facilities
	// /**
	// * Creates the string button field editor for selecting one model element
	// among a set.
	// *
	// * @param parent The parent composite hosting this field editor.
	// * @param key The key to retrieve value inside the preference store.
	// * @param label The label to display before the text field.
	// */
	// protected EObjectFieldEditor createPathFieldEditor(Composite parent,
	// String key, String label)
	// {
	// EObjectFieldEditor fieldEditor = new EObjectFieldEditor(key, label,
	// parent);
	// fieldEditor.setLabelProvider(defaultLabelProvider);
	// fieldEditor.setAdvancedLabelProvider(advancedLabelProvider);
	// fieldEditor.setPage(this);
	// fieldEditor.setPreferenceStore(getPreferenceStore());
	// return fieldEditor;
	// }

	/**
	 * Loads the preferences
	 */
	private void loadPreferences() {
		syncModeEditor.load();
		diagramPath.load();
		modelPath.load();
		srcPath.load();
		typePath.load();
		extPath.load();

		extractPackagesFromModel();
	}

	/**
	 * Stores the preferences
	 */
	private void storePreferences() {
		syncModeEditor.store();
		diagramPath.store();
		modelPath.store();
		srcPath.store();
		typePath.store();
		extPath.store();
	}

	/**
	 * Loads the default preferences
	 */
	private void loadDefaultPreferences() {
		syncModeEditor.loadDefault();
		diagramPath.loadDefault();
		modelPath.loadDefault();
		srcPath.loadDefault();
		typePath.loadDefault();
		extPath.loadDefault();

		extractPackagesFromModel();
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		storePreferences();
		return super.performOk();
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		loadDefaultPreferences();
		super.performDefaults();
	}

	@Override
	protected String getBundleId() {
		return BundleConstants.BUNDLE_ID;
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		// nothing to do while initializing
	}

	/**
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {
		for (Resource rsc : rscSet.getResources()) {
			rsc.unload();
		}
		rscSet = null;
	}
}
