package com.tecnoven.notify.ui.core;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.tecnoven.notify.domain.UserData;
import com.tecnoven.notify.ui.ChangePasswordDialog;
import com.tecnoven.notify.ui.MainFrame;
import com.tecnoven.notify.ui.NotifyQuerySMSAmountDispatch;
import com.tecnoven.notify.ui.core.func.IFunctDispatch;
import com.tecnoven.notify.util.ConnectionDB;

/**
 * 
 * @author hector
 *
 */
public class MainTreeNodeListener implements TreeSelectionListener {
	/**
	 * 
	 */
	private Map<Integer, IFunctDispatch> functions;
	/**
	 * 
	 */
	private MainFrame main;
	/**
	 * 
	 * @param frame
	 */
	public MainTreeNodeListener(MainFrame frame){
		this.main = frame;
		this.functions = this.main.getMapFunctionality();
	}

	/**
	 * 
	 */
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		Object[] nodes = path.getPath();

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[(nodes.length-1)];
		Node nd = (Node) node.getUserObject();
		IFunctDispatch function = this.functions.get(nd.getNodeId());
		if(function!=null){
			if (function instanceof JDialog) {
				JDialog dialog = null;
				Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
				if (function instanceof ChangePasswordDialog){
					dialog= (ChangePasswordDialog)function;
				}
				else if (function instanceof NotifyQuerySMSAmountDispatch){
					dialog = (NotifyQuerySMSAmountDispatch)function;
					((NotifyQuerySMSAmountDispatch)dialog).initState();
				}
				dialog.setLocation((d.width - dialog.getWidth()) / 2,
						(d.height - dialog.getHeight()) / 2);
				dialog.setVisible(true);
			}else{
				this.main.getMain().removeAll();
				this.main.getMain().setLayout(new GridLayout(1,1));
				this.main.getMain().add((Component)function);
				function.initState();
				this.main.getMain().updateUI();
			}
		}else if (!nd.isExpandable()){
			int value = JOptionPane.showConfirmDialog(this.main,
					"¿Desea cerrar la aplicaci\u00f3n?",
					"Salir", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			if (value == 0){ 
				UserData user = this.main.getUserLogged();
				user.setLogged(false);
				ConnectionDB.getInstance().getSession().update(user);
				ConnectionDB.getInstance().getSession().flush();
				this.main.dispose();
				System.exit(0);
			}
		}
	}
}