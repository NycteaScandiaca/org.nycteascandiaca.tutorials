package org.nycteascandiaca.tutorials.library.ui.commands;

import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.ui.ISelectionProvider;
import org.nycteascandiaca.tutorials.library.ui.Selection;

public class SetSelectionCommand implements ICommand
{
	private final ISelectionProvider provider;
	
	private final Selection newSelection;
	
	private Selection oldSelection;
	
	public SetSelectionCommand(ISelectionProvider provider, Selection newSelection)
	{
		this.provider = provider;
		this.newSelection = newSelection;
	}

	@Override
	public void execute()
	{
		oldSelection = provider.getSelection();
		provider.setSelection(newSelection);
	}

	@Override
	public void undo()
	{
		provider.setSelection(oldSelection);
	}

	@Override
	public void redo()
	{
		provider.setSelection(newSelection);
	}

}
