package org.example.jface.treeexample;

import java.util.ArrayList;

public class Pages extends ArrayList<Page> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Session parent;

	public Pages(Session parent) {
		super();
		setParent(parent);
	}

	public void setParent(Session parent) {
		this.parent = parent;
	}
	
	public Session getParent() {
		return this.parent;
	}
}