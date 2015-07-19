package org.nycteascandiaca.tutorials.library.commands;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CommandStack
{
	private final Stack<ICommand> undoable;
	private final Stack<ICommand> redoable;
	
	private final int maxSize;
	
	private final List<ICommandStackListener> listeners;
	
	public CommandStack(int maxSize)
	{
		undoable = new Stack<ICommand>();
		redoable = new Stack<ICommand>();
		
		this.maxSize = maxSize;
		
		this.listeners = new LinkedList<ICommandStackListener>();
	}
	
	public void execute(ICommand command)
	{
		command.execute();
		while (undoable.size() >= maxSize)
		{
			undoable.remove(0);
		}
		undoable.push(command);
		redoable.clear();
		
		fireCommandStackChanged();
	}
	
	public boolean canUndo()
	{
		return !undoable.isEmpty();
	}
	
	public void undo()
	{
		if (!canUndo())
		{
			throw new RuntimeException();
		}
		
		ICommand command = undoable.pop();
		command.undo();
		redoable.push(command);
		
		fireCommandStackChanged();
	}
	
	public boolean canRedo()
	{
		return !redoable.isEmpty();
	}
	
	public void redo()
	{
		if (!canRedo())
		{
			throw new RuntimeException();
		}
		
		ICommand command = redoable.pop();
		command.redo();
		undoable.push(command);
		
		fireCommandStackChanged();
	}
	
	public void clear()
	{
		undoable.clear();
		redoable.clear();
		
		fireCommandStackChanged();
	}
	
	public void addCommandStackListener(ICommandStackListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeCommandStackListener(ICommandStackListener listener)
	{
		listeners.remove(listener);
	}
	
	private void fireCommandStackChanged()
	{
		listeners.forEach(current -> current.commandStackChanged());
	}
}
