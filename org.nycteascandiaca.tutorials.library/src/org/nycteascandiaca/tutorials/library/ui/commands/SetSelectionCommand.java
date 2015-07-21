package org.nycteascandiaca.tutorials.library.ui.commands;

import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.ui.ISelectionProvider;
import org.nycteascandiaca.tutorials.library.ui.Selection;

public class SetSelectionCommand implements ICommand
{
	private final ISelectionProvider provider;
	
	private final Selection selection;
	
	public SetSelectionCommand(ISelectionProvider provider, Selection selection)
	{
		this.provider = provider;
		this.selection = selection;
	}

	@Override
	public void execute()
	{
		provider.setSelection(selection);
	}

	@Override
	public void undo()
	{
		// Do nothing
	}

	@Override
	public void redo()
	{
		provider.setSelection(selection);
	}

}
