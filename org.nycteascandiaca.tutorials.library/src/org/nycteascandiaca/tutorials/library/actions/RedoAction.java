package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.commands.ICommandStackListener;

@SuppressWarnings("serial")
public class RedoAction extends AbstractAction implements ICommandStackListener, IAction
{
	RedoAction()
	{
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
		commandStack.redo();
	}

	@Override
	public void commandStackChanged()
	{
		CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
		setEnabled(commandStack.canRedo());
	}

	@Override
	public void initialize()
	{
		CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
		commandStack.addCommandStackListener(this);
		setEnabled(commandStack.canRedo());
	}

	@Override
	public void dispose()
	{
		CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
		commandStack.removeCommandStackListener(this);
	}
}
