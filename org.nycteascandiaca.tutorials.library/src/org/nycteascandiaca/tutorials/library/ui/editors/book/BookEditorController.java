package org.nycteascandiaca.tutorials.library.ui.editors.book;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.EBookCategory;
import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.PropertyChangeEvent;
import org.nycteascandiaca.tutorials.library.model.commands.SetCommand;
import org.nycteascandiaca.tutorials.library.ui.editors.IEditorController;

public class BookEditorController implements IEditorController<Book, BookEditor>, IPropertyChangeListener, FocusListener, ItemListener
{
	private final BookEditor view;
	
	private final BookAuthorsListModel authorsListModel;
	
	private final Book model;
	
	public BookEditorController(Book model, BookEditor view)
	{
		this.view = view;
		this.model = model;
		this.authorsListModel = new BookAuthorsListModel(model);
	}
	
	@Override
	public Book getModel()
	{
		return model;
	}
	
	@Override
	public BookEditor getView()
	{
		return view;
	}
	
	@Override
	public void initialize()
	{
		model.addPropertyChangeListener(this);
		
		view.getTitleTextField().addFocusListener(this);
		view.getCategoryComboBox().addItemListener(this);
		view.getPublicationDateTextField().addFocusListener(this);
		view.getPagesTextField().addFocusListener(this);
		view.getDescriptionTextArea().addFocusListener(this);
		view.getAuthorsList().setModel(authorsListModel);
		
		authorsListModel.initialize();
		
		setViewId(model.getId());
		setViewTitle(model.getTitle());
		setViewCategory(model.getCategory());
		setViewPublicationDate(model.getPublicationDate());
		setViewPages(model.getPages());
		setViewDescription(model.getDescription());
		setViewAuthors(model.getAuthors());
	}
	
	@Override
	public void dispose()
	{
		model.removePropertyChangeListener(this);
		
		view.getTitleTextField().removeFocusListener(this);
		view.getCategoryComboBox().removeFocusListener(this);
	}
	
	@SuppressWarnings("unchecked")
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
			case BOOK__TITLE:
			{
				setViewTitle((String)event.getNewValue());
				break;
			}
			case BOOK__CATEGORY:
			{
				setViewCategory((EBookCategory)event.getNewValue());
				break;
			}
			case BOOK__PUBLICATION_DATE:
			{
				setViewPublicationDate((LocalDate)event.getNewValue());
				break;
			}
			case BOOK__PAGES:
			{
				setViewPages((int)event.getNewValue());
				break;
			}
			case BOOK__DESCRIPTION:
			{
				setViewDescription((String)event.getNewValue());
				break;
			}
			case BOOK__AUTHORS:
			{
				setViewAuthors((List<Author>)event.getNewValue());
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
	
	private void setViewTitle(String title)
	{
		JTextField titleTextField = view.getTitleTextField();
		titleTextField.setText(title);
	}
	
	private void setViewCategory(EBookCategory category)
	{
		JComboBox<EBookCategory> categoryComboBox = view.getCategoryComboBox();
		categoryComboBox.setSelectedItem(category);
	}
	
	private void setViewPublicationDate(LocalDate publicationDate)
	{
		JTextField publicationDateTextField = view.getPublicationDateTextField();
		publicationDateTextField.setText(String.valueOf(publicationDate));
	}
	
	private void setViewPages(int pages)
	{
		JTextField pagesTextField = view.getPagesTextField();
		pagesTextField.setText(String.valueOf(pages));
	}
	
	private void setViewDescription(String description)
	{
		JTextArea descriptionTextArea = view.getDescriptionTextArea();
		descriptionTextArea.setText(String.valueOf(description));
	}
	
	private void setViewAuthors(List<Author> newValue)
	{	
		List<Author> authorList = (List<Author>)newValue;
		Author[] listData = null;
		if (authorList != null)
		{
			listData = new Author[authorList.size()];
			listData = authorList.toArray(listData);
		}
		
		JList<Author> authorsList = view.getAuthorsList();
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
		
		if (e.getSource() == view.getTitleTextField())
		{
			JTextField titleTextField = view.getTitleTextField();
			String title = titleTextField.getText();
			if (!Objects.equals(model.getTitle(), title))
			{
				ICommand command = new SetCommand(model, EModelProperty.BOOK__TITLE, title);
				commandStack.execute(command);
			}
		}
		else if (e.getSource() == view.getPublicationDateTextField())
		{
			JTextField publicationDateTextField = view.getPublicationDateTextField();
			// TODO ...
		}
		else if (e.getSource() == view.getPagesTextField())
		{
			JTextField pagesTextField = view.getPagesTextField();
			// TODO ...			
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

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if (e.getStateChange() == ItemEvent.SELECTED)
		{
			CommandStack commandStack = Application.INSTANCE.getModelManager().getCommandStack();
			
			if (e.getSource() == view.getCategoryComboBox())
			{
				JComboBox<EBookCategory> categoryComboBox = view.getCategoryComboBox();
				EBookCategory category = (EBookCategory)categoryComboBox.getSelectedItem();
				if (category != model.getCategory())
				{
					ICommand command = new SetCommand(model, EModelProperty.BOOK__CATEGORY, category);
					commandStack.execute(command);
				}
			}
		}		
	}
}