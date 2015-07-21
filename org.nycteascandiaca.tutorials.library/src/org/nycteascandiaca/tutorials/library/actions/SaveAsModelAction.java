package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.IModelManagerListener;
import org.nycteascandiaca.tutorials.library.model.Library;
import org.nycteascandiaca.tutorials.library.ui.LibraryWindow;

@SuppressWarnings("serial")
class SaveAsModelAction extends AbstractAction implements IAction, IModelManagerListener
{
	
	SaveAsModelAction()
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
		LibraryWindow window = Application.INSTANCE.getUIManager().getWindow();
		
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showSaveDialog(window);
		if (result != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		
		File file = fileChooser.getSelectedFile();		
		try
		{
			Application.INSTANCE.getModelManager().saveModelAs(file.toPath());
		}
		catch (JAXBException e1)
		{
			JOptionPane.showMessageDialog
			(
					window,
					"Cannot save model: " + e1.getMessage(),
					"Save Model",
					JOptionPane.ERROR_MESSAGE
			);
		}
	}

	@Override
	public void modelOpened(Library library, Path path)
	{
		setEnabled(true);
	}

	@Override
	public void modelSaved(Library library, Path path)
	{
		
	}

	@Override
	public void modelClosed(Library library)
	{
		setEnabled(false);
	}
}
