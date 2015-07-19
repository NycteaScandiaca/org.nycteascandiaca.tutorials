package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.nio.file.Path;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.IModelManagerListener;
import org.nycteascandiaca.tutorials.library.model.Library;

@SuppressWarnings("serial")
class SaveModelAction extends AbstractAction implements IAction, IModelManagerListener
{
	SaveModelAction()
	{
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Application.INSTANCE.getModelManager().saveModel();
	}

	@Override
	public void modelOpened(Library library, Path path)
	{
		setEnabled(path != null);
	}

	@Override
	public void modelSaved(Library library, Path path)
	{
		setEnabled(path != null);
	}

	@Override
	public void modelClosed(Library library)
	{
		setEnabled(false);
	}
}
