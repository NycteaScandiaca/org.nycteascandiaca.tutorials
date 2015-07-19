package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.IModelManagerListener;
import org.nycteascandiaca.tutorials.library.model.Library;

@SuppressWarnings("serial")
class SaveAsModelAction extends AbstractAction implements IAction, IModelManagerListener
{
	
	SaveAsModelAction()
	{
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showSaveDialog(Application.INSTANCE.getUIManager().getWindow());
		if (result != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		
		File file = fileChooser.getSelectedFile();		
		Application.INSTANCE.getModelManager().saveModelAs(file.toPath());
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
