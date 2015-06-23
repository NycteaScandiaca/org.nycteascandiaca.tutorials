package org.nycteascandiaca.tutorials.library.ui;

public class SelectionChangeEvent
{
	private final ISelectionProvider source;
	private final Selection selection;
	
	public SelectionChangeEvent(ISelectionProvider source, Selection selection)
	{
		this.source = source;
		this.selection = selection;
	}
	
	public ISelectionProvider getSource()
	{
		return source;
	}
	
	public Selection getSelection()
	{
		return selection;
	}
}
