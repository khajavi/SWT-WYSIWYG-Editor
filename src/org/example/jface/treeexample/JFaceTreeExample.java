package org.example.jface.treeexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Text;
import org.xml.sax.SAXException;

public class JFaceTreeExample extends ApplicationWindow {

	public JFaceTreeExample() {
		super(null);
	}

	@Override
	protected Control createContents(Composite parent) {
		// parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new FormLayout());

		final Sash sashForm = new Sash(parent, SWT.VERTICAL);
		FormData data = new FormData();
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		data.left = new FormAttachment(80, 0);
		sashForm.setLayoutData(data);

		final TreeViewer tv = new TreeViewer(parent, SWT.BORDER
				| SWT.RIGHT_TO_LEFT);
		data = new FormData();
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		data.left = new FormAttachment(sashForm, 0);
		data.right = new FormAttachment(100, 0);
		tv.getTree().setLayoutData(data);
		tv.setContentProvider(new MyTreeContentProvider());
		// tv.setLabelProvider(new MyTreeLabelProvider());

		final Editor editor = new Editor(parent, SWT.BORDER);
		// final Text text = new Text(parent, SWT.MULTI | SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(sashForm, 0);
		editor.setLayoutData(data);
		editor.getEditor().setHTMLContent("<b>Hello World!</b>");

		GetContent content = null;

		try {
			FileInputStream fis = new FileInputStream(
					new File(
							"content.xml"));

			content = new GetContent(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(content.handler.getCourse().getSessions().get(0)
				.getTitle());

		tv.setInput(content.handler.getCourse());

		final NewSessionAction action = new NewSessionAction(content, tv);

		final MenuManager menuManager = new MenuManager();
		menuManager.setRemoveAllWhenShown(true);
		menuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				IStructuredSelection selection = (IStructuredSelection) tv
						.getSelection();
				if (!selection.isEmpty()) {
					if (selection.getFirstElement() instanceof Page) {
						// // Page page = (Page) selection.getFirstElement();
						action.setText("New Page");
					} else if (selection.getFirstElement() instanceof Session) {
						Session session = (Session) selection.getFirstElement();
						action.setText("new Session");
					}
					System.out.println(selection.getFirstElement().getClass());
					menuManager.add(action);
				}
			}
		});
		tv.getControl().setMenu(menuManager.createContextMenu(tv.getControl()));

		sashForm.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// Reattach to the left edge, and use the x value of the event
				// to
				// determine the offset from the left
				((FormData) sashForm.getLayoutData()).left = new FormAttachment(
						0, event.x);
				// Until the parent window does a layout, the sash will not be
				// redrawn in
				// its new location. So, force a layout.
				sashForm.getParent().layout();
			}
		});

		tv.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tv
						.getSelection();

				if (!selection.isEmpty()) {
					if (selection.getFirstElement() instanceof Page) {
						Page page = (Page) selection.getFirstElement();
						editor.getEditor().setHTMLContent(
								"<div style=\"direction: rtl; text-align: right; font-family: Iranian Sans\">"
								+ page.getContent()
								+ "</div>"
						);
					} else if (selection.getFirstElement() instanceof Session) {
						Session session = (Session) selection.getFirstElement();
						action.setText("new Session");
					}
					// System.out.println(selection.getFirstElement().getClass());
					// menuManager.add(action);
				}

			}
		});

		return parent;
	}

	public static void main(String[] args) {
		JFaceTreeExample tree = new JFaceTreeExample();
		tree.setBlockOnOpen(true);
		tree.open();
		tree.getShell().getDisplay().dispose();
	}
}
