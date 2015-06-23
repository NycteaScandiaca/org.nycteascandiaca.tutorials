package org.nycteascandiaca.tutorials.library.model.impl;

import java.util.LinkedList;
import java.util.List;

import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IAuthor;
import org.nycteascandiaca.tutorials.library.model.IBook;
import org.nycteascandiaca.tutorials.library.model.ILibrary;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeEvent;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeListener;

public class Library extends ModelElement implements ILibrary
{
	private String name;
	
	private final List<IBook> books;
	
	private final List<IAuthor> authors;
	
	private final List<IPropertyChangeListener> propertyChangeListeners;

	Library()
	{
		this.books = new ModelList<IBook>(this, EModelProperty.LIBRARY__BOOKS, true);
		this.authors = new ModelList<IAuthor>(this, EModelProperty.LIBRARY__AUTHORS, true);
		this.propertyChangeListeners = new LinkedList<IPropertyChangeListener>();
	}
	
	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public List<IBook> getBooks()
	{
		return books;
	}

	@Override
	public List<IAuthor> getAuthors()
	{
		return authors;
	}
	
	@Override
	public void addPropertyChangeListener(IPropertyChangeListener listener)
	{
		propertyChangeListeners.add(listener);
	}
	
	@Override
	public void removePropertyChangeListener(IPropertyChangeListener listener)
	{
		propertyChangeListeners.remove(listener);
	}
	
	@Override
	protected void firePropertyChanged(IPropertyChangeEvent event)
	{
		propertyChangeListeners.forEach(current -> current.propertyChange(event));
	}
	
	@Override
	public Object getPropertyValue(EModelProperty property)
	{
		switch(property)
		{
			case LIBRARY__NAME:
			{
				return getName();
			}
			case LIBRARY__AUTHORS:
			{
				return getAuthors();
			}
			case LIBRARY__BOOKS:
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
			case LIBRARY__NAME:
			{
				setName((String)value);
				break;
			}
			default:
			{
				super.setPropertyValue(property, value);
			}
		}
	}
}
