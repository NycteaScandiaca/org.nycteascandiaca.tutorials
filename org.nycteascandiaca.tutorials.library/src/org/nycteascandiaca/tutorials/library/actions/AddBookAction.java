package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CompoundCommand;
import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.EBookCategory;
import org.nycteascandiaca.tutorials.library.model.ModelFactory;
import org.nycteascandiaca.tutorials.library.model.ModelManager;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.commands.AddCommand;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.ISelectionChangedListener;
import org.nycteascandiaca.tutorials.library.ui.ISelectionProvider;
import org.nycteascandiaca.tutorials.library.ui.Selection;
import org.nycteascandiaca.tutorials.library.ui.SelectionChangeEvent;
import org.nycteascandiaca.tutorials.library.ui.UIManager;
import org.nycteascandiaca.tutorials.library.ui.commands.SetSelectionCommand;
import org.nycteascandiaca.tutorials.library.ui.tree.ModelTreeModel;

@SuppressWarnings("serial")
public class AddBookAction extends AbstractAction implements ISelectionChangedListener
{
	AddBookAction()
	{
		setEnabled(false);
	}
	
	@Override
	public void initialize()
	{
		ISelectionProvider selectionProvider = (ISelectionProvider)Application.INSTANCE.getUIManager().getView(EView.MODEL_TREE);
		selectionProvider.addSelectionChangedListener(this);
	}
	
	@Override
	public void dispose()
	{
		ISelectionProvider selectionProvider = (ISelectionProvider)Application.INSTANCE.getUIManager().getView(EView.MODEL_TREE);
		selectionProvider.removeSelectionChangedListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		ModelManager modelManager = Application.INSTANCE.getModelManager();
		ModelFactory modelFactory = modelManager.getModelFactory();
		UIManager uiManager = Application.INSTANCE.getUIManager();
		
		Book book = modelFactory.createBook();
		book.setTitle("New Book");
		book.setCategory(EBookCategory.SCENCE);
		book.setPublicationDate(LocalDate.now());
		
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
	public void selectionChanged(SelectionChangeEvent event)
	{
		Selection selection = event.getSelection();
		List<Object> elements = selection.getElements();
		if (elements.size() == 1)
		{
			Object first = elements.get(0);
			if (first == ModelTreeModel.FakeNode.BOOKS)
			{
				setEnabled(true);
				return;
			}
		}
		setEnabled(false);
	}
}
