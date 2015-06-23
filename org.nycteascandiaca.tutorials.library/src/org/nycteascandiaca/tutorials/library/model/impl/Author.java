package org.nycteascandiaca.tutorials.library.model.impl;

import java.util.List;

import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IAuthor;
import org.nycteascandiaca.tutorials.library.model.IBook;

public class Author extends ModelElement implements IAuthor
{
	private String firstName;
	
	private String lastName;
	
	private String description;
	
	private final List<IBook> books;
	
	Author()
	{
		books = new ModelList<IBook>(this, EModelProperty.AUTHOR__BOOKS, false);
	}
	
	@Override
	public String getFirstName()
	{
		return firstName;
	}

	@Override
	public void setFirstName(String firstName)
	{
		firePropertyChanged(EModelProperty.AUTHOR__FIRST_NAME, this.firstName, this.firstName = firstName);
	}

	@Override
	public String getLastName()
	{
		return lastName;
	}

	@Override
	public void setLastName(String lastName)
	{
		firePropertyChanged(EModelProperty.AUTHOR__LAST_NAME, this.lastName, this.lastName = lastName);
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public void setDescription(String description)
	{
		firePropertyChanged(EModelProperty.AUTHOR__DESCRIPTION, this.description, this.description = description);
	}

	@Override
	public List<IBook> getBooks()
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
