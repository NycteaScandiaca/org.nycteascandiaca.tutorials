package org.nycteascandiaca.tutorials.library.commands;

import java.util.LinkedList;

public class CompoundCommand implements ICommand
{
	private final LinkedList<ICommand> commands;
	
	public CompoundCommand()
	{
		commands = new LinkedList<ICommand>();
	}
	
	public void addCommand(ICommand command)
	{
		commands.add(command);
	}
	
	@Override
	public void execute()
	{
		commands.forEach(command -> command.execute());
	}

	@Override
	public void undo()
	{
		commands.descendingIterator().forEachRemaining(command -> command.undo());
	}

	@Override
	public void redo()
	{
		commands.forEach(command -> command.redo());
	}
}
