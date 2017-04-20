/**
 * 
 */
package com.tecnoven.notify.ui.core;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreePath;

/**
 * @author hector
 *
 */
public class TreeUIExtended  extends BasicTreeUI{

	private JTree reference;
	
	public TreeUIExtended(JTree ref){
		super();
		reference = ref;
	}

	/* (non-Javadoc)
	 * @see javax.swing.plaf.basic.BasicTreeUI#createMouseListener()
	 */
	@Override
	protected MouseListener createMouseListener() {
        return new MouseListener() {
            private boolean selectedOnPress;
            public void mouseClicked(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {
                // This is our custom check to reject single clicks
		    	TreePath tp = TreeUIExtended.this.reference.getPathForLocation(e.getX(), e.getY());

                if (e.getClickCount() == 1 && tp!=null) {
                    return;
                }
                if (!e.isConsumed()) {
                    handleSelection(e);
                    selectedOnPress = true;
                }
                else {
                    selectedOnPress = false;
                }
            }

            void handleSelection(MouseEvent e) {
                if (reference != null && reference.isEnabled()) {
                    if (isEditing(reference) && reference.getInvokesStopCellEditing() &&
                        !stopEditing(reference))
                    {
                        return;
                    }

                    if (reference.isRequestFocusEnabled()) {
                    	reference.requestFocus();
                    }
                    TreePath path = getClosestPathForLocation(reference, e.getX(),
                                                              e.getY());

                    if (path != null) {
                        Rectangle bounds = getPathBounds(reference, path);

                        if (e.getY() > (bounds.y + bounds.height)) {
                            return;
                        }

                        // Preferably checkForClickInExpandControl could take
                        // the Event to do this it self!
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            checkForClickInExpandControl(path, e.getX(), e.getY());
                        }

                        int x = e.getX();

                        // Perhaps they clicked the cell itself. If so,
                        // select it.
                        if (x > bounds.x) {
                            if (x <= (bounds.x + bounds.width) &&
                                !startEditing(path, e))
                            {
                                selectPathForEvent(path, e);
                            }
                        }
                    }
                }
            }

            @SuppressWarnings("unused")
			public void mouseDragged(MouseEvent e) {}
            @SuppressWarnings("unused")
			public void mouseMoved(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {
                // This is our custom check to reject single clicks                        
                if (e.getClickCount() == 1) {
                    return;
                }
                if ((!e.isConsumed()) && (!selectedOnPress)) {
                    handleSelection(e);
                }
            }
        };
    }
}
