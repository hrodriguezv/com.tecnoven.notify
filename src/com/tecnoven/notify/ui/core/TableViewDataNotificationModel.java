/**
 * 
 */
package com.tecnoven.notify.ui.core;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.tecnoven.notify.domain.NotificationData;
import com.tecnoven.notify.domain.RecordData;
import com.tecnoven.notify.domain.TagData;
import com.tecnoven.notify.lang.RecordOrderTagComparator;
import com.tecnoven.notify.lang.TagComparator;
import com.tecnoven.notify.util.ConsistencyDataUtil;


/**
 * @author hector
 *
 */
public class TableViewDataNotificationModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3334013108619206311L;

	private String[] columnNames;


	private Object[][] data;

	private NotificationData object;
	
	public TableViewDataNotificationModel(NotificationData data){
		this.object = data;
		this.populateDataTable();
	}
	
	private void populateDataTable(){
		//iterate over columns
		ArrayList<TagData> tags = new ArrayList<TagData>(this.object.getTemplate().getDefinition().getTags()); 
		Collections.sort(tags, new TagComparator());
		columnNames = new String[tags.size()];
		int numberCol = 0;
		for (TagData tag : tags) {
			columnNames[numberCol] = tag.getTag();
			numberCol++;
		}
		// iterate over records
		ArrayList<RecordData> records = new ArrayList<RecordData>(this.object.getRecords());
		TagData pk = this.object.getTemplate().getDefinition().getPKTagData();
		
		ArrayList<RecordData> ids = ConsistencyDataUtil.filterByTag(records, pk);
		data = new Object[ids.size()][columnNames.length];
		
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i).getValue();
			ArrayList<RecordData> rs = ConsistencyDataUtil.filterByValue(records, id);
			if (rs.size() == columnNames.length){
				Collections.sort(rs, new RecordOrderTagComparator());
				for (int j = 0; j < rs.size(); j++) {
					data[i][j] = rs.get(j).getValue();
				}
			}else{
				JOptionPane.showConfirmDialog(null, "Data cargada con (" + rs.size() +") columnas, y la plantilla define ("+columnNames.length+")" ,
						"Data Inconsistente", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
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
			return false;				
	}

	/*
	 * Implement this method, if your table data will change.
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}

