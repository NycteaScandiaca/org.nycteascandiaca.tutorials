package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.ModelManager;

@SuppressWarnings("serial")
class NewModelAction extends AbstractAction implements IAction
{
	NewModelAction()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ModelManager modelManager = Application.INSTANCE.getModelManager();
		modelManager.newModel();
	}
}
