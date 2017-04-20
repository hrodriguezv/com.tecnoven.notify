/*
 * MainFrame.java
 *
 * Created on May 14, 2009, 2:20 AM
 */
package com.tecnoven.notify.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;


import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.tecnoven.notify.controller.LicenseManager;
import com.tecnoven.notify.domain.LicenseEntity;
import com.tecnoven.notify.domain.UserData;
import com.tecnoven.notify.ui.core.MainTreeNodeListener;
import com.tecnoven.notify.ui.core.Node;
import com.tecnoven.notify.ui.core.TreeUIExtended;
import com.tecnoven.notify.ui.core.func.IFunctDispatch;
import com.tecnoven.notify.util.ConnectionDB;
import com.tecnoven.notify.util.Constants;

/**
 * 
 * @author hector
 */
public class MainFrame extends javax.swing.JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2428389419523570735L;

	protected DefaultTreeModel treeModel = null;

	private static MainFrame reference;

	private UserData userLogged;

	/** Creates new form NewJFrame */
	private MainFrame() {
		super(LicenseManager.getInstance().getLicense().getBarTitle());
		setTitle(LicenseManager.getInstance().getLicense().getBarTitle());
		initComponents();
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.reorderPanel();
		reference = this;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
		d.height = d.height - 50;
		addWindowListener(this);
	}

	public MainFrame(UserData user) {
		this();
		this.userLogged = user;
		if (!this.isMainUserLogged()) {
			JOptionPane
					.showConfirmDialog(
							this,
							"Acceso indebido, debe realizar el ingreso a la aplicaci\u00f3n",
							"Error", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		setVisible(true);
	}

	private boolean isMainUserLogged() {
		return (this.userLogged != null) ? this.userLogged.isLogged() : false;
	}

	public UserData getUserLogged() {
		return this.userLogged;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code
	// <editor-fold defaultstate="collapsed"
	// desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		contentPane = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		treeMenu = new javax.swing.JTree();
		main = new javax.swing.JPanel();

		getContentPane().setLayout(new java.awt.GridLayout(1, 0));

//		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		jScrollPane1.setViewportView(treeMenu);

		org.jdesktop.layout.GroupLayout mainLayout = new org.jdesktop.layout.GroupLayout(
				main);
		main.setLayout(mainLayout);
		mainLayout.setHorizontalGroup(mainLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 0,
				Short.MAX_VALUE));
		mainLayout.setVerticalGroup(mainLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 0,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout contentPaneLayout = new org.jdesktop.layout.GroupLayout(
				contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout
				.setHorizontalGroup(contentPaneLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								contentPaneLayout
										.createSequentialGroup()
										.add(
												jScrollPane1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												main,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(469, 469, 469)));
		contentPaneLayout
				.setVerticalGroup(contentPaneLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								contentPaneLayout
										.createSequentialGroup()
										.add(
												contentPaneLayout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jScrollPane1)
														.add(
																main,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.add(33, 33, 33)));
		getContentPane().add(contentPane);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * 
	 *
	 */
	private void rebuildTreeMenu() {
		this.jScrollPane1.remove(this.treeMenu);
		this.buildTreeMenu();
		this.jScrollPane1.setViewportView(this.treeMenu);
		this.main.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}

	private JPanel buildInitForm() {
		FormLayout layout = new FormLayout(
				"fill:50px:grow(.25), fill:50px:grow", "fill:"
						+ this.getHeight() + "px:grow");
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		// Obtain a reusable constraints object to place components in the grid.
		CellConstraints cc = new CellConstraints();
		builder.add(this.jScrollPane1, cc.xy(1, 1));
		builder.add(this.main, cc.xy(2, 1));
		JPanel content = builder.getPanel();
		return content;
	}

	/**
	 * 
	 *
	 */
	private void reorderPanel() {
		this.rebuildTreeMenu();
		this.contentPane.removeAll();
		JPanel content = this.buildInitForm();
		content.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.contentPane.setLayout(new BoxLayout(this.contentPane,
				BoxLayout.X_AXIS));
		this.contentPane.add(content);
	}

	/**
	 * 
	 *
	 */
	private void buildTreeMenu() {
		// Retrieve the three icons
		Icon leafIcon = new javax.swing.ImageIcon(getClass().getResource(
		Constants.PATH_TO_ICONS + "leaf.png"));
		Icon openIcon = new javax.swing.ImageIcon(getClass().getResource(
		Constants.PATH_TO_ICONS + "fopen.png"));
		Icon closedIcon = new javax.swing.ImageIcon(getClass().getResource(
		Constants.PATH_TO_ICONS + "fclose.png"));

		LicenseEntity license = LicenseManager.getInstance().getLicense();

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(new Node(0,
				"Sistema de Gesti\u00f3n de Mensajes"));
		this.mapFunctionality.put(0, new MainPanelDispatch());
		DefaultMutableTreeNode parent = top;

		DefaultMutableTreeNode templateNode = new DefaultMutableTreeNode(
				new Node(1, "Mensaje", true));
		parent.add(templateNode);

		DefaultMutableTreeNode node = new DefaultMutableTreeNode(new Node(2,
				"Definir Plantilla"));

		this.mapFunctionality.put(2, new NotifyTemplateDefinitionDispatch());
		templateNode.add(node);

		DefaultMutableTreeNode firstChild = new DefaultMutableTreeNode(
				new Node(3, "Actualizar Contenido"));
		this.mapFunctionality.put(3, new NotifyTemplateDispatch());
		templateNode.add(firstChild);

		DefaultMutableTreeNode notificationParent = new DefaultMutableTreeNode(
				new Node(4, "Procesamiento", true));

		node = new DefaultMutableTreeNode(new Node(5,
				"Gestionar Notificaciones"));
		this.mapFunctionality.put(5, new NotifyNotificationDispatch());
		notificationParent.add(node);

		node = new DefaultMutableTreeNode(new Node(6, "Cargar Datos"));
		this.mapFunctionality.put(6, new NotifyBulkNotificationDispatch());
		notificationParent.add(node);

		parent.add(notificationParent);

		DefaultMutableTreeNode configurationParent = new DefaultMutableTreeNode(
				new Node(7, "Configuraci\u00f3n", true));
		parent.add(configurationParent);

		if (license.getSupportedServices().contains(Constants.EMAIL_SERVICE)) {
			node = new DefaultMutableTreeNode(new Node(8, "Servidor de Correo"));
			this.mapFunctionality.put(8, new NotifyConfigurationDispatch());
			configurationParent.add(node);
		}

		node = new DefaultMutableTreeNode(new Node(9, "Cambio de Clave"));
		this.mapFunctionality.put(9, new ChangePasswordDialog());
		configurationParent.add(node);

		if (license.getSupportedServices().contains(Constants.SMS_SERVICE)) {
			node = new DefaultMutableTreeNode(
					new Node(10, "Consulta Saldo SMS"));
			this.mapFunctionality.put(10, new NotifyQuerySMSAmountDispatch());
			configurationParent.add(node);
		}

		node = new DefaultMutableTreeNode(new Node(11, "Salir"));
		parent.add(node);

		this.treeModel = new DefaultTreeModel(top);
		this.treeMenu = new JTree(this.treeModel);

		// Update only one tree instance
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)this.treeMenu.getCellRenderer();
		renderer.setLeafIcon(leafIcon);
		renderer.setClosedIcon(closedIcon);
		renderer.setOpenIcon(openIcon);

		// Change defaults so that all new tree components will have new icons
		UIManager.put("Tree.leafIcon", leafIcon);
		UIManager.put("Tree.openIcon", openIcon);
		UIManager.put("Tree.closedIcon", closedIcon);

		this.treeMenu.setShowsRootHandles(true);
		this.treeMenu.setEditable(false);
		this.treeMenu.setSelectionRow(0);
		this.treeMenu.addTreeSelectionListener(new MainTreeNodeListener(this));
		this.treeMenu.setUI(new TreeUIExtended(this.treeMenu));
	}

	public static MainFrame getInstance() {
		return reference;
	}

	private Map<Integer, IFunctDispatch> mapFunctionality = new HashMap<Integer, IFunctDispatch>();

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel contentPane;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JPanel main;
	private javax.swing.JTree treeMenu;

	// End of variables declaration//GEN-END:variables

	/**
	 * @return the main
	 */
	public javax.swing.JPanel getMain() {
		return main;
	}

	/**
	 * @return the mapFunctionality
	 */
	public Map<Integer, IFunctDispatch> getMapFunctionality() {
		return mapFunctionality;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		this.closeWindow();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	private void closeWindow() {
		UserData user = this.getUserLogged();
		user.setLogged(false);
		ConnectionDB.getInstance().getSession().update(user);
		ConnectionDB.getInstance().getSession().flush();
		this.dispose();
		System.exit(0);
	}
}