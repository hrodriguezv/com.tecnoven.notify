package com.tecnoven.notify.ui.core;
/**
 * 
 * @author hector
 *
 */
public class Node {
	/**
	 * 
	 */
	protected int nodeId;

	/**
	 * 
	 */
	protected String name;
	
	private boolean expandable;

	/**
	 * 
	 * @param id
	 * @param text
	 */
	public Node(int id, String text) {
		nodeId = id;
		name = text;
	}

	/**
	 * 
	 * @param id
	 * @param text
	 */
	public Node(int id, String text, boolean ex) {
		this(id,text);
		this.expandable = ex;
	}

	/**
	 * 
	 * @return
	 */
	public int getNodeId() {
		return nodeId;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 */
	public String toString() {
		return name;
	}

	/**
	 * @return the expandable
	 */
	public boolean isExpandable() {
		return expandable;
	}
	
	
}
