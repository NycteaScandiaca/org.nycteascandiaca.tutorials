package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.nycteascandiaca.tutorials.library.Application;

@SuppressWarnings("serial")
public class ExitAction extends AbstractAction implements IAction
{
	public ExitAction()
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Application.INSTANCE.exit();
	}

	@Override
	public void initialize()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}
}
