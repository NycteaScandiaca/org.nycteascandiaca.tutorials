package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.ModelManager;

@SuppressWarnings("serial")
public class CloseModelAction extends AbstractAction implements IAction
{
	CloseModelAction()
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		ModelManager modelManager = Application.INSTANCE.getModelManager();
		modelManager.setLibrary(null);
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
