package org.nycteascandiaca.tutorials.library.ui.editors.library;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.Library;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.edit.PropertyChangeEvent;
import org.nycteascandiaca.tutorials.library.model.edit.commands.SetCommand;
import org.nycteascandiaca.tutorials.library.ui.UIUtils;
import org.nycteascandiaca.tutorials.library.ui.editors.IEditorController;

public class LibraryEditorController implements IEditorController<Library, LibraryEditor>, IPropertyChangeListener, FocusListener
{
	private final Library model;
	
	private final LibraryEditor view;
	
	public LibraryEditorController(Library model, LibraryEditor view)
	{
		this.model = model;
		this.view = view;
	}

	@Override
	public LibraryEditor getView()
	{
		return view;
	}

	@Override
	public Library getModel()
	{
		return model;
	}

	@Override
	public void initialize()
	{
		model.addPropertyChangeListener(this);
		
		view.getNameTextField().addFocusListener(this);
		view.getDescriptionTextArea().addFocusListener(this);
		
		setViewId(model.getId());
		setViewName(model.getName());
		setViewDescription(model.getDescription());
	}

	@Override
	public void dispose()
	{
		model.removePropertyChangeListener(this);
		
		view.getNameTextField().removeFocusListener(this);
		view.getDescriptionTextArea().removeFocusListener(this);
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
			case LIBRARY__NAME:
			{
				setViewName((String)event.getNewValue());
				break;
			}
			case LIBRARY__DESCRIPTION:
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
	
	private void setViewName(String firstName)
	{
		JTextField nameTextField = view.getNameTextField();
		nameTextField.setText(UIUtils.toString(firstName));
	}
	
	private void setViewDescription(String description)
	{
		JTextArea descriptionTextArea = view.getDescriptionTextArea();
		descriptionTextArea.setText(UIUtils.toString(description));
	}
	
	@Override
	public void focusGained(FocusEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
		if (e.getSource() == view.getNameTextField())
		{
			JTextField nameTextField = view.getNameTextField();
			String viewValue = nameTextField.getText();
			String modelValue = UIUtils.toString(model, EModelProperty.LIBRARY__NAME);
			if (!Objects.equals(modelValue, viewValue))
			{
				ICommand command = new SetCommand(model, EModelProperty.LIBRARY__NAME, viewValue);
				commandStack.execute(command);
			}
		}
		else if (e.getSource() == view.getDescriptionTextArea())
		{
			JTextArea descriptionTextArea = view.getDescriptionTextArea();
			String viewValue = descriptionTextArea.getText();
			String modelValue = UIUtils.toString(model, EModelProperty.LIBRARY__DESCRIPTION);
			if (!Objects.equals(modelValue, viewValue))
			{
				ICommand command = new SetCommand(model, EModelProperty.LIBRARY__DESCRIPTION, viewValue);
				commandStack.execute(command);
			}
		}
	}
}
