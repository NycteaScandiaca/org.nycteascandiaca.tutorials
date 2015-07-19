package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;

import org.nycteascandiaca.tutorials.library.Application;

@SuppressWarnings("serial")
class ExitAction extends AbstractAction implements IAction
{
	ExitAction()
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Application.INSTANCE.exit();
	}
}
