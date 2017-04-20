/**
 * 
 */
package com.tecnoven.notify.ui.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import com.tecnoven.notify.domain.NotificationData;


/**
 * @author hector
 *
 */
public class TableNotificationModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3334013108619206311L;

	private String[] columnNames = { "Fecha", "Plantilla", "Nº Envios",
			"Nº Notificaciones", "Tipo de Notificaci\u00f3n", "Selecci\u00f3n" };

	private Object[][] data;

	private List<NotificationData> list;
	
	public TableNotificationModel(List<NotificationData> l){
		this.list = l;
		this.populateDataTable();
	}
	
	private void populateDataTable(){
		data = new Object[list.size()][columnNames.length];
		int row = 0;
		for (NotificationData object : list) {
			data[row][0] = this.dateToString(object.getCreated(), "EEE, d MMM yyyy HH:mm:ss");
			data[row][1] = object.getTemplate().getName();
			data[row][2] = object.getNumberSent();
			data[row][3] = object.getNumberNotification();
			data[row][4] = object.getType();
			data[row][5] = false;
			row++;
		}
	}
	
    private String dateToString(java.util.Date date, String pattern){
    	SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
    	return format.format(date);
    }

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	public NotificationData getNotificationValueAt(int row, int col) {
		return this.list.get(row);
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn’t implement this method, then the last column
	 * would contain text ("true"/"false"), rather than a check box.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * This method is implemented if we want to make the cell editable.
	 */
	public boolean isCellEditable(int row, int col) {
		return col == 5;
	}

	/*
	 * Implement this method, if your table data will change.
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	/**
	 * 
	 * @return
	 */
	public Long[] getSelectedItemsId(){
		ArrayList<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < this.getRowCount(); i++) {
			if ((Boolean)this.getValueAt(i, 5)){
				ids.add(this.getNotificationValueAt(i, 0).getNotificationId());
			}
		}
		return ids.toArray(new Long[]{});
	}
}
