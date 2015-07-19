package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.commands.ICommandStackListener;

@SuppressWarnings("serial")
class UndoAction extends AbstractAction implements ICommandStackListener, IAction
{
	UndoAction()
	{
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Application.INSTANCE.getModelManager().getCommandStack().undo();
	}

	@Override
	public void commandStackChanged()
	{
		setEnabled(Application.INSTANCE.getModelManager().getCommandStack().canUndo());
	}

	@Override
	public void initialize()
	{
		CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
		commandStack.addCommandStackListener(this);
		setEnabled(commandStack.canUndo());
	}

	@Override
	public void dispose()
	{
		CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
		commandStack.removeCommandStackListener(this);
	}
}
