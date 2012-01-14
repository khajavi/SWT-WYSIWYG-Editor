package org.example.jface.treeexample;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import chrriis.dj.sweet.components.JHTMLEditor;
import chrriis.dj.sweet.components.JHTMLEditor.HTMLEditorImplementation;

public class Editor extends Composite {
	private JHTMLEditor editor;
	public Editor(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		String configurationScript = "FCKConfig.ToolbarSets[\"Default\"] = [\n"
				+ "['Source','DocProps','-','Save','NewPage','Preview','-','Templates'],\n"
				+ "['Cut','Copy','Paste','PasteText','PasteWord','-','Print','SpellCheck'],\n"
				+ "['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],\n"
				+ "['Form','Checkbox','Radio','TextField','Textarea','Select','Button','ImageButton','HiddenField'],\n"
				+ "'/',\n"
				+ "['Style','FontFormat','FontName','FontSize'],\n"
				+ "['TextColor','BGColor'],\n"
				+ "'/',\n"
				+ "['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],\n"
				+ "['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote'],\n"
				+ "['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],\n"
				+ "['Link','Unlink','Anchor'],\n"
				+ "['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak', '-', 'ShowBlocks'],\n"
				+ "];\n" + "FCKConfig.ToolbarCanCollapse = false;\n";
		editor = new JHTMLEditor(this,
				HTMLEditorImplementation.FCKEditor,
				JHTMLEditor.FCKEditorOptions
						.setCustomJavascriptConfiguration(configurationScript));
	}

	public JHTMLEditor getEditor() {
		return editor;
	}
}