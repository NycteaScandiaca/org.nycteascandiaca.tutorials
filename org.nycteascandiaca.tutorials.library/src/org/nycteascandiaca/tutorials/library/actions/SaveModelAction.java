package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.nio.file.Path;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;

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
	public void initialize()
	{
		Application.INSTANCE.getModelManager().addModelManagerListener(this);
	}
	
	@Override
	public void dispose()
	{
		Application.INSTANCE.getModelManager().removeModelManagerListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Application.INSTANCE.getModelManager().saveModel();
		}
		catch (JAXBException e1)
		{
			JOptionPane.showMessageDialog
			(
					Application.INSTANCE.getUIManager().getWindow(),
					"Cannot save model: " + e1.getMessage(),
					"Save Model",
					JOptionPane.ERROR_MESSAGE
			);
		}
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
