package org.nycteascandiaca.tutorials.library.model;

import java.util.List;

import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.EPropertyChangeEventType;

public class Author extends ModelElement
{
	private String firstName;
	
	private String lastName;
	
	private String description;
	
	private final List<Book> books;
	
	Author(String id)
	{
		super(id);
		
		books = new ModelList<Book>(this, EModelProperty.AUTHOR__BOOKS, false);
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		firePropertyChanged
		(
				EModelProperty.AUTHOR__FIRST_NAME,
				EPropertyChangeEventType.SET,
				this.firstName,
				this.firstName = firstName
		);
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		firePropertyChanged
		(
				EModelProperty.AUTHOR__LAST_NAME,
				EPropertyChangeEventType.SET,
				this.lastName,
				this.lastName = lastName
		);
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		firePropertyChanged
		(
				EModelProperty.AUTHOR__DESCRIPTION,
				EPropertyChangeEventType.SET,
				this.description,
				this.description = description
		);
	}
	
	public List<Book> getBooks()
	{
		return books;
	}

	@Override
	public Object getPropertyValue(EModelProperty property)
	{
		switch(property)
		{
			case AUTHOR__FIRST_NAME:
			{
				return getFirstName();
			}
			case AUTHOR__LAST_NAME:
			{
				return getLastName();
			}
			case AUTHOR__DESCRIPTION:
			{
				return getDescription();
			}
			case AUTHOR__BOOKS:
			{
				return getBooks();
			}
			default:
			{
				return super.getPropertyValue(property);
			}
		}
	}
	
	@Override
	public void setPropertyValue(EModelProperty property, Object value)
	{
		switch(property)
		{
			case AUTHOR__FIRST_NAME:
			{
				setFirstName((String)value);
				break;
			}
			case AUTHOR__LAST_NAME:
			{
				setLastName((String)value);
				break;
			}
			case AUTHOR__DESCRIPTION:
			{
				setDescription((String)value);
				break;
			}
			default:
			{
				super.setPropertyValue(property, value);
			}
		}
	}
}
