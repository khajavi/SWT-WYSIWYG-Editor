package org.example.jface.treeexample;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class MyTreeContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Course) {
			Course course = (Course) parentElement;
			return course.getSessions().toArray();
		} else if (parentElement instanceof Session) {
			Session session = (Session) parentElement;
			return session.getPages().toArray();
		} else { // if (parentElement instanceof Page)
			return null;
		}
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Page) {
			Page page = (Page) element;
			return page.getParent().getParent();
		} else if (element instanceof Session) {
			Session session = (Session) element;
			return session.getParent().getParent();
		} else {
			Course course = (Course) element;
			return course.getParent();
		}
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Page) {
			return false;
		} else if (element instanceof Session) {
			Session session = (Session) element;
			return session.getPages().size() > 0;
		} else {
			Course course = (Course) element;
			return course.getSessions().size() > 0;
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Course) {
			Course course = (Course) inputElement;
			return course.getSessions().toArray();
		} else if (inputElement instanceof Session) {
			Session session = (Session) inputElement;
			return session.getPages().toArray();
		} else { // if (parentElement instanceof Page)
			return null;
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

}
