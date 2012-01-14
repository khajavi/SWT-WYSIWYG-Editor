package org.example.jface.treeexample;

import java.util.ArrayList;

public class Sessions extends ArrayList<Session> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Course parent;

	public Sessions(Course parent) {
		super();
		setParent(parent);
	}
	
	public void setParent(Course parent) {
		this.parent = parent;
	}
	
	public Course getParent() {
		return this.parent;
	}
}