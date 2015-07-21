package org.nycteascandiaca.tutorials.library.model;

import java.util.LinkedList;
import java.util.List;

import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.edit.PropertyChangeEvent;

public class Library extends ModelElement
{
	private String name;
	
	private final List<Book> books;
	
	private final List<Author> authors;
	
	private final List<IPropertyChangeListener> propertyChangeListeners;

	Library(String id)
	{
		super(id);
		
		this.books = new ModelList<Book>(this, EModelProperty.LIBRARY__BOOKS, true);
		this.authors = new ModelList<Author>(this, EModelProperty.LIBRARY__AUTHORS, true);
		this.propertyChangeListeners = new LinkedList<IPropertyChangeListener>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<Book> getBooks()
	{
		return books;
	}
	
	public List<Author> getAuthors()
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
	protected void firePropertyChanged(PropertyChangeEvent event)
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
