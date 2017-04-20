/**
 * 
 */
package com.tecnoven.notify.ui.core.func;

/**
 * @author hector
 *
 */
public interface IFunctDispatch {
	boolean unchanged = true;

	public void setCatalogObject(ICatalogueObject object);
	
	public void initState();
	
	public void ok();
	
	public void cancel();
}
