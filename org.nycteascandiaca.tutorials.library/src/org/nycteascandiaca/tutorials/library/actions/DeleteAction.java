package org.nycteascandiaca.tutorials.library.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CompoundCommand;
import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.ModelElement;
import org.nycteascandiaca.tutorials.library.model.ModelManager;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.commands.RemoveCommand;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.ISelectionChangedListener;
import org.nycteascandiaca.tutorials.library.ui.ISelectionProvider;
import org.nycteascandiaca.tutorials.library.ui.Selection;
import org.nycteascandiaca.tutorials.library.ui.SelectionChangeEvent;
import org.nycteascandiaca.tutorials.library.ui.commands.SetSelectionCommand;

@SuppressWarnings("serial")
public class DeleteAction extends AbstractAction implements ISelectionChangedListener
{

	DeleteAction()
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
		ISelectionProvider selectionProvider = (ISelectionProvider)Application.INSTANCE.getUIManager().getView(EView.MODEL_TREE);
		Selection selection = selectionProvider.getSelection();
		List<Object> elements = selection.getElements();
				
		CompoundCommand commands = new CompoundCommand();
		
		ICommand command = new SetSelectionCommand(selectionProvider, Selection.EMPTY);
		commands.addCommand(command);
		for (Object element : elements)
		{
			ModelElement container = null;
			EModelProperty property = null;
			if (element instanceof Author)
			{
				container = ((Author)element).getOwner();
				property = EModelProperty.LIBRARY__AUTHORS;
			}
			else if (element instanceof Book)
			{
				container = ((Book)element).getOwner();
				property = EModelProperty.LIBRARY__BOOKS;
			}
						
			command = new RemoveCommand
			(
					container,
					property,
					(ModelElement)element
			);
			commands.addCommand(command);
		}
		command = new SetSelectionCommand(selectionProvider, Selection.EMPTY);
		commands.addCommand(command);
		
		modelManager.getCommandStack().execute(commands);
	}

	@Override
	public void selectionChanged(SelectionChangeEvent event)
	{
		Selection selection = event.getSelection();		
		EModelProperty property = getModelProperty(selection);		
		setEnabled(property != null);
	}

	private EModelProperty getModelProperty(Selection selection)
	{
		EModelProperty result = null;
		List<Object> elements = selection.getElements();		
		for (Object element : elements)
		{
			EModelProperty currentProperty = getModelProperty(element);
			if (result == null)
			{
				result = currentProperty;
			}
			
			if (currentProperty == null || currentProperty != result)
			{
				return null;
			}
		}
		return result;
	}
	
	private EModelProperty getModelProperty(Object element)
	{
		if (element instanceof Author)
		{
			return EModelProperty.LIBRARY__AUTHORS;
		}
		else if (element instanceof Book)
		{
			return EModelProperty.LIBRARY__BOOKS;
		}
		return null;
	}
}
