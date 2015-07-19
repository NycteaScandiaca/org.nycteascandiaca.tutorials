package org.nycteascandiaca.tutorials.library.ui.editors;

import javax.swing.JComponent;

public interface IEditorController<M, V extends JComponent>
{
	public V getView();
	
	public M getModel();
	
	public void initialize();
	
	public void dispose();
}
