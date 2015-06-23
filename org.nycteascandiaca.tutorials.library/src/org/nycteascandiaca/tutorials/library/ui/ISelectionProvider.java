package org.nycteascandiaca.tutorials.library.ui;

public interface ISelectionProvider
{
	public Selection getSelection();
	
	public void setSelection(Selection selection);
	
	public void addSelectionChangedListener(ISelectionChangedListener listener);
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener);
}
