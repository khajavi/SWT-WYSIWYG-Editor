package org.example.jface.treeexample;

import java.io.Serializable;

public class Session implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sessions parent;
	private Pages pages;
	private String title;

	public Session(Sessions parent) {
		setParent(parent);
		setPages(null);
		setTitle(null);
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public Pages getPages() {
		return pages;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public void setParent(Sessions parent) {
		this.parent = parent;
	}
	
	public Sessions getParent() {
		return this.parent;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}
}