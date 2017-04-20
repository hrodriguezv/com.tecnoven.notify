/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnoven.notify.ui.core;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import com.tecnoven.notify.util.Constants;

/*
 * FileSelectorDialog.java
 *
 * Created on Jul 10, 2010, 10:38:21 AM
 */

/**
 *
 * @author hector
 */
public class FileSelectorDialog extends javax.swing.JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2932768101334045114L;
	
	private SortedListModel sourceListModel = new SortedListModel();

	private long sizeFiles = 0;

	private HashMap<String, File> files = new HashMap<String, File>();

	private HashSet<File> erased = new HashSet<File>();
	
	
	/** Creates new form FileSelectorDialog */
    public FileSelectorDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
		this.setLocationRelativeTo(parent);
		this.settingValueObjects();
    }

    private void settingValueObjects(){
		this.okButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				Constants.PATH_TO_ICONS + "ok.png")));
		this.okButton.setText("<html><font size='3'>Aceptar</font></html>");
		this.cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
		Constants.PATH_TO_ICONS + "cancel.png")));
		this.cancelButton.setText("<html><font size='3'>Cancelar</font></html>");
        this.sourceList.setModel(sourceListModel);
        this.addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
		Constants.PATH_TO_ICONS + "add.png")));
        this.addButton.setToolTipText("Agregar");
        this.removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
		Constants.PATH_TO_ICONS + "remove.png")));
        this.removeButton.setToolTipText("Remover");
    }
    
	public void clearSourceListModel() {
		this.sourceListModel.clear();
	}

	public void addSourceElements(ListModel newValue) {
		this.fillListModel(this.sourceListModel, newValue);
	}

	public void setSourceElements(ListModel newValue) {
		this.clearSourceListModel();
		this.addSourceElements(newValue);
	}

	private void fillListModel(SortedListModel model, ListModel newValues) {
		int size = newValues.getSize();
		for (int i = 0; i < size; i++) {
			model.add(newValues.getElementAt(i));
		}
	}

	public void addSourceElements(Object newValue[]) {
		this.fillListModel(this.sourceListModel, newValue);
	}

	public void setSourceElements(Object newValue[]) {
		this.clearSourceListModel();
		this.addSourceElements(newValue);
	}

	private void fillListModel(SortedListModel model, Object newValues[]) {
		model.addAll(newValues);
	}

	@SuppressWarnings("rawtypes")
	public Iterator sourceIterator() {
		return this.sourceListModel.iterator();
	}

	public void setSourceCellRenderer(ListCellRenderer newValue) {
		this.sourceList.setCellRenderer(newValue);
	}

	public ListCellRenderer getSourceCellRenderer() {
		return this.sourceList.getCellRenderer();
	}

	public void setVisibleRowCount(int newValue) {
		this.sourceList.setVisibleRowCount(newValue);
	}

	public int getVisibleRowCount() {
		return this.sourceList.getVisibleRowCount();
	}

	public void setSelectionBackground(Color newValue) {
		this.sourceList.setSelectionBackground(newValue);
	}

	public Color getSelectionBackground() {
		return this.sourceList.getSelectionBackground();
	}

	public void setSelectionForeground(Color newValue) {
		this.sourceList.setSelectionForeground(newValue);
	}

	public Color getSelectionForeground() {
		return this.sourceList.getSelectionForeground();
	}

	
	private void removeFiles() {
		Object selected[] = this.sourceList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			this.sourceListModel.removeElement(selected[i]);
			File fileRemoved = files.get(selected[i]);
			try {
				this.files.remove(fileRemoved.getCanonicalPath());
				this.erased.add(fileRemoved);
				this.sizeFiles = (this.sizeFiles >= 0) ? this.sizeFiles
						- fileRemoved.length() : 0;
			} catch (IOException e) {
			}
		}
		double maskSize = this.sizeFiles / (1024);
		NumberFormat df = NumberFormat.getInstance(new Locale("es", "VE"));
		DecimalFormat nf = (DecimalFormat) df;
		nf.applyPattern("##0.0");
		this.sizeLabel.setText(nf.format(maskSize) + " kb");
	}

	private void addFiles() {
		int returnVal = this.jFileChooser1.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File fileSelected = this.jFileChooser1.getSelectedFile();
			try {
				this.sourceListModel.add(fileSelected.getCanonicalPath());
				if (!this.files.containsKey(fileSelected.getCanonicalPath())) {
					this.files.put(fileSelected.getCanonicalPath(), fileSelected);
					this.sizeFiles = this.sizeFiles + fileSelected.length();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			NumberFormat df = NumberFormat.getInstance(new Locale("es","VE"));
			DecimalFormat nf = (DecimalFormat)df;
			nf.applyPattern("##0.0");
			double maskSize = this.sizeFiles/(1024);
			this.sizeLabel.setText(nf.format(maskSize) + " kb");
		}
	}

	public ArrayList<File> getSelectedFiles() {
		ArrayList<File> listOfFiles = new ArrayList<File>();
		Iterator<String> iterator = this.files.keySet().iterator();
		for (;iterator.hasNext();) {
			String key = iterator.next();
			File file = this.files.get(key);
			listOfFiles.add(file);
		}
		return listOfFiles;
	}
	
	private void pressOkButton(){
		this.setVisible(false);
	}
	
	private void pressCancelButton(){
		this.restoreFiles();
		this.setVisible(false);
	}
	
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("serial")
	private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        sourceList = new javax.swing.JList();
        sizeLabel = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Archivos adjuntos");
        setResizable(false);

        sourceList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(sourceList);

        sizeLabel.setText("--");

        okButton.setText("Aceptar");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jToolBar1.setOrientation(1);
        jToolBar1.setRollover(true);

        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addButton);

        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sizeLabel)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addComponent(okButton)
                .addGap(48, 48, 48)
                .addComponent(cancelButton)
                .addGap(185, 185, 185))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sizeLabel))
                    .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        this.addFiles();
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        this.removeFiles();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.pressOkButton();
    }                                                            

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    	this.pressCancelButton();
    }//GEN-LAST:event_cancelButtonActionPerformed


    private void restoreFiles(){
    	for (File file : this.erased) {
			try {
				this.sourceListModel.add(file.getCanonicalPath());
				if (!this.files.containsKey(file.getCanonicalPath())) {
					this.files.put(file.getCanonicalPath(), file);
					this.sizeFiles = this.sizeFiles + file.length();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton okButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JList sourceList;
    // End of variables declaration//GEN-END:variables

}
