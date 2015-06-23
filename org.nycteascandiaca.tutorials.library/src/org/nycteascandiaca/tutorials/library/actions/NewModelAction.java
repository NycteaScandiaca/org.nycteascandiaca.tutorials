package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.ModelManager;

@SuppressWarnings("serial")
public class NewModelAction extends AbstractAction implements IAction
{
	NewModelAction()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ModelManager modelManager = Application.INSTANCE.getModelManager();
		modelManager.openDemoModel();
		
		/*IModelFactory modelFactory = modelManager.getModelFactory();
		
		ILibrary library = modelFactory.createLibrary();
		library.setName("New Library");
		
		modelManager.setLibrary(library);*/
	}

	@Override
	public void initialize()
	{
		
	}

	@Override
	public void dispose()
	{
		
	}
}
