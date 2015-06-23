package org.nycteascandiaca.tutorials.library.commands;

public interface ICommand
{
	public void execute();
	
	public void undo();
	
	public void redo();
}
