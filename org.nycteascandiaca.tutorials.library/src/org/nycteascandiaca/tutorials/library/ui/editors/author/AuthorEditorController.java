package org.nycteascandiaca.tutorials.library.ui.editors.author;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.edit.PropertyChangeEvent;
import org.nycteascandiaca.tutorials.library.model.edit.commands.SetCommand;
import org.nycteascandiaca.tutorials.library.ui.UIUtils;
import org.nycteascandiaca.tutorials.library.ui.editors.IEditorController;

public class AuthorEditorController implements IEditorController<Author, AuthorEditor>, IPropertyChangeListener, FocusListener
{
	private final AuthorEditor view;
	
	private final AuthorBooksListModel booksListModel;
	
	private final Author model;

	public AuthorEditorController(Author model, AuthorEditor view)
	{
		this.model = model;
		this.view = view;
		this.booksListModel = new AuthorBooksListModel(model);
	}
	
	@Override
	public AuthorEditor getView()
	{
		return view;
	}

	@Override
	public Author getModel()
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
		view.getBooksList().setModel(booksListModel);
		
		booksListModel.initialize();
		
		setViewId(model.getId());
		setViewFirstName(model.getFirstName());
		setViewLastName(model.getLastName());
		setViewDescription(model.getDescription());
		//setViewBooks(model.getBooks());
	}

	@Override
	public void dispose()
	{
		model.removePropertyChangeListener(this);
		
		view.getFirstNameTextField().removeFocusListener(this);
		view.getLastNameTextField().removeFocusListener(this);
		view.getDescriptionTextArea().removeFocusListener(this);
		
		booksListModel.dispose();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event)
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
		idTextField.setText(UIUtils.toString(id));
	}
	
	private void setViewFirstName(String firstName)
	{
		JTextField firstNameTextField = view.getFirstNameTextField();
		firstNameTextField.setText(UIUtils.toString(firstName));
	}
	
	private void setViewLastName(String lastName)
	{
		JTextField lastNameTextField = view.getLastNameTextField();
		lastNameTextField.setText(UIUtils.toString(lastName));
	}
	
	private void setViewDescription(String description)
	{
		JTextArea descriptionTextArea = view.getDescriptionTextArea();
		descriptionTextArea.setText(UIUtils.toString(description));
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
			String viewValue = firstNameTextField.getText();
			String modelValue = UIUtils.toString(model, EModelProperty.AUTHOR__FIRST_NAME);
			if (!Objects.equals(modelValue, viewValue))
			{
				ICommand command = new SetCommand(model, EModelProperty.AUTHOR__FIRST_NAME, viewValue);
				commandStack.execute(command);
			}
		}
		else if (e.getSource() == view.getLastNameTextField())
		{
			JTextField lastNameTextField = view.getLastNameTextField();
			String viewValue = lastNameTextField.getText();
			String modelValue = UIUtils.toString(model, EModelProperty.AUTHOR__LAST_NAME);
			if (!Objects.equals(modelValue, viewValue))
			{
				ICommand command = new SetCommand(model, EModelProperty.AUTHOR__LAST_NAME, viewValue);
				commandStack.execute(command);
			}
		}
		else if (e.getSource() == view.getDescriptionTextArea())
		{
			JTextArea descriptionTextArea = view.getDescriptionTextArea();
			String viewValue = descriptionTextArea.getText();
			String modelValue = UIUtils.toString(model, EModelProperty.AUTHOR__DESCRIPTION);
			if (!Objects.equals(modelValue, viewValue))
			{
				ICommand command = new SetCommand(model, EModelProperty.AUTHOR__DESCRIPTION, viewValue);
				commandStack.execute(command);
			}
		}
	}
}
