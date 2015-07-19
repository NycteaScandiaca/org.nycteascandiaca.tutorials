package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import org.nycteascandiaca.tutorials.library.Application;

@SuppressWarnings("serial")
class OpenModelAction extends AbstractAction implements IAction
{

	OpenModelAction()
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(Application.INSTANCE.getUIManager().getWindow());
		if (result != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		
		File file = fileChooser.getSelectedFile();
		Application.INSTANCE.getModelManager().openModel(file.toPath());
	}
}
