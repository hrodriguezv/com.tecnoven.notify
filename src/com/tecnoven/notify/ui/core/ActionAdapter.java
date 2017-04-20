/**
 * 
 */
package com.tecnoven.notify.ui.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.tecnoven.notify.ui.NotifyNotificationDispatch;


/**
 * @author hector
 *
 */
public class ActionAdapter implements ActionListener {
	NotifyNotificationDispatch adapter;

	public ActionAdapter(NotifyNotificationDispatch adapter) {
		this.adapter = adapter;
	}

	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		if (item.getText() == "Ver Datos") {
			adapter.viewData(e);
		} else if (item.getText() == "Eliminar") {
			adapter.deleteRow(e);
			adapter.initState();
		}else if (item.getText() == "Notificar") {
			adapter.notify(e);
		}
	}
}
