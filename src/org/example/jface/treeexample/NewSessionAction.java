package org.example.jface.treeexample;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

public class NewSessionAction extends Action {
	private GetContent content;
	private TreeViewer tv;
	
	public NewSessionAction(GetContent content, TreeViewer tv) {
		this.content = content;
		this.tv = tv;
	}
	
	@Override
	public void run() {
		Page p = new Page(content.handler.getCourse().getSessions().get(0).getPages());
		p.setTitle("صفحهٔ جدید");
		content.handler.getCourse().getSessions().get(0).getPages().add(p);
		tv.setInput(content.handler.getCourse());
	}
}
