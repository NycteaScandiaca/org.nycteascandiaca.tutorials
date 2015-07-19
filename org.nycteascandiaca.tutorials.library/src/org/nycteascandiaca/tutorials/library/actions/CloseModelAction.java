package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.nio.file.Path;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.IModelManagerListener;
import org.nycteascandiaca.tutorials.library.model.Library;
import org.nycteascandiaca.tutorials.library.model.ModelManager;

@SuppressWarnings("serial")
class CloseModelAction extends AbstractAction implements IAction, IModelManagerListener
{
	CloseModelAction()
	{
		setEnabled(false);
	}
	
	@Override
	public void initialize()
	{
		ModelManager modelManager = Application.INSTANCE.getModelManager();
		modelManager.addModelManagerListener(this);
	}
	
	@Override
	public void dispose()
	{
		ModelManager modelManager = Application.INSTANCE.getModelManager();
		modelManager.removeModelManagerListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		ModelManager modelManager = Application.INSTANCE.getModelManager();
		modelManager.closeModel();
	}

	@Override
	public void modelOpened(Library library, Path path)
	{
		setEnabled(true);
	}

	@Override
	public void modelSaved(Library library, Path path)
	{
		// Do nothing
	}

	@Override
	public void modelClosed(Library library)
	{
		setEnabled(false);
	}
}
