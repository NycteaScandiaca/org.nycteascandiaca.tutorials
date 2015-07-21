package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.nio.file.Path;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CompoundCommand;
import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.IModelManagerListener;
import org.nycteascandiaca.tutorials.library.model.Library;
import org.nycteascandiaca.tutorials.library.model.ModelFactory;
import org.nycteascandiaca.tutorials.library.model.ModelManager;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.commands.AddCommand;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.ISelectionProvider;
import org.nycteascandiaca.tutorials.library.ui.Selection;
import org.nycteascandiaca.tutorials.library.ui.UIManager;
import org.nycteascandiaca.tutorials.library.ui.commands.SetSelectionCommand;

@SuppressWarnings("serial")
public class AddBookAction extends AbstractAction implements IModelManagerListener
{
	AddBookAction()
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
		ModelManager modelManager = Application.INSTANCE.getModelManager();
		ModelFactory modelFactory = modelManager.getModelFactory();
		UIManager uiManager = Application.INSTANCE.getUIManager();
		
		Book book = modelFactory.createBook();
		book.setTitle("New Book");
		
		CompoundCommand commands = new CompoundCommand();
		ICommand command = new AddCommand
		(
				modelManager.getModel(),
				EModelProperty.LIBRARY__BOOKS,
				book
		);
		commands.addCommand(command);
		
		ISelectionProvider selectionProvider = (ISelectionProvider)uiManager.getView(EView.MODEL_TREE);
		command = new SetSelectionCommand(selectionProvider, new Selection(book));
		commands.addCommand(command);
		
		modelManager.getCommandStack().execute(commands);
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
