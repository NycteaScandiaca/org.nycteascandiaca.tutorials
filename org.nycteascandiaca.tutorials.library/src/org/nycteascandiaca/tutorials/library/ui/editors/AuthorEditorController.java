package org.nycteascandiaca.tutorials.library.ui.editors;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Objects;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IAuthor;
import org.nycteascandiaca.tutorials.library.model.IBook;
import org.nycteascandiaca.tutorials.library.model.IModelElement;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeEvent;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.commands.SetCommand;
import org.nycteascandiaca.tutorials.library.ui.IController;

public class AuthorEditorController implements IController, IPropertyChangeListener, FocusListener
{
	private final AuthorEditor view;
	
	private final IAuthor model;

	AuthorEditorController(IAuthor model, AuthorEditor view)
	{
		this.model = model;
		this.view = view;
	}
	
	@Override
	public Component getView()
	{
		return view;
	}

	@Override
	public IModelElement getModel()
	{
		return model;
	}

	@Override
	public void initialize()
	{
		model.addPropertyChangeListener(this);
		
		view.getFirstNameTextField().addFocusListener(this);
		view.getLastNameTextField().addFocusListener(this);
		view.getDescriptionTextArea().addFocusListener(this);
		
		setViewId(model.getId());
		setViewFirstName(model.getFirstName());
		setViewLastName(model.getLastName());
		setViewDescription(model.getDescription());
		setViewBooks(model.getBooks());
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void propertyChange(IPropertyChangeEvent event)
	{
		if (event.getSource() != model)
		{
			return;
		}
		
		switch(event.getProperty())
		{
			case MODEL_ELEMENT__ID:
			{
				setViewId((String)event.getNewValue());
				break;
			}
			case AUTHOR__FIRST_NAME:
			{
				setViewFirstName((String)event.getNewValue());
				break;
			}
			case AUTHOR__LAST_NAME:
			{
				setViewLastName((String)event.getNewValue());
				break;
			}
			case AUTHOR__DESCRIPTION:
			{
				setViewDescription((String)event.getNewValue());
				break;
			}
			case AUTHOR__BOOKS:
			{
				setViewBooks((List<IBook>)event.getNewValue());
				break;
			}
			default:
			{
				// Do nothing
				break;
			}
		}
	}
	
	private void setViewId(String id)
	{
		JTextField idTextField = view.getIdTextField();
		idTextField.setText(id);
	}
	
	private void setViewFirstName(String firstName)
	{
		JTextField firstNameTextField = view.getFirstNameTextField();
		firstNameTextField.setText(firstName);
	}
	
	private void setViewLastName(String lastName)
	{
		JTextField lastNameTextField = view.getLastNameTextField();
		lastNameTextField.setText(lastName);
	}
	
	private void setViewDescription(String description)
	{
		JTextArea descriptionTextArea = view.getDescriptionTextArea();
		descriptionTextArea.setText(String.valueOf(description));
	}
	
	private void setViewBooks(List<IBook> newValue)
	{	
		List<IBook> bookList = (List<IBook>)newValue;
		IBook[] listData = null;
		if (bookList != null)
		{
			listData = new IBook[bookList.size()];
			listData = bookList.toArray(listData);
		}
		
		JList<IBook> authorsList = view.getBooksList();
		authorsList.setListData(listData);
	}
	
	@Override
	public void focusGained(FocusEvent e)
	{
		// Do nothing
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
		if (e.getSource() == view.getFirstNameTextField())
		{
			JTextField firstNameTextField = view.getFirstNameTextField();
			String firstName = firstNameTextField.getText();
			if (!Objects.equals(model.getFirstName(), firstName))
			{
				ICommand command = new SetCommand(model, EModelProperty.AUTHOR__FIRST_NAME, firstName);
				commandStack.execute(command);
			}
		}
		else if (e.getSource() == view.getLastNameTextField())
		{
			JTextField lastNameTextField = view.getLastNameTextField();
			String lastName = lastNameTextField.getText();
			if (!Objects.equals(model.getLastName(), lastName))
			{
				ICommand command = new SetCommand(model, EModelProperty.AUTHOR__LAST_NAME, lastName);
				commandStack.execute(command);
			}
		}
		else if (e.getSource() == view.getDescriptionTextArea())
		{
			JTextArea descriptionTextArea = view.getDescriptionTextArea();
			String description = descriptionTextArea.getText();
			if (!Objects.equals(model.getDescription(), description))
			{
				ICommand command = new SetCommand(model, EModelProperty.BOOK__DESCRIPTION, description);
				commandStack.execute(command);
			}
		}
	}
}
