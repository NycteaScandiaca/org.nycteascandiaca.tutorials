package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.ui.LibraryWindow;

@SuppressWarnings("serial")
class OpenModelAction extends AbstractAction implements IAction
{

	OpenModelAction()
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		LibraryWindow window = Application.INSTANCE.getUIManager().getWindow();
		
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(window);
		if (result != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		
		File file = fileChooser.getSelectedFile();
		try
		{
			Application.INSTANCE.getModelManager().openModel(file.toPath());
		}
		catch (FileNotFoundException | JAXBException e1)
		{
			JOptionPane.showMessageDialog
			(
					window,
					"Cannot open model: " + e1.getMessage(),
					"Open Model",
					JOptionPane.ERROR_MESSAGE
			);
		}
	}
}
