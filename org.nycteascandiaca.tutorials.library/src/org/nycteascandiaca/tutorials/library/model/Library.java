package org.nycteascandiaca.tutorials.library.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.EPropertyChangeEventType;
import org.nycteascandiaca.tutorials.library.model.edit.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.edit.PropertyChangeEvent;

@XmlRootElement(name = "Library")
public class Library extends ModelElement
{
	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Description")
	private String description;
	
	@XmlElementWrapper(name = "Books")
    @XmlElement(name = "Book")
	private final List<Book> books;
	
	@XmlElementWrapper(name = "Authors")
    @XmlElement(name = "Author")
	private final List<Author> authors;
	
	@XmlTransient
	private final List<IPropertyChangeListener> propertyChangeListeners;

	Library()
	{
		super(null);
		
		this.books = new ModelList<Book>(this, EModelProperty.LIBRARY__BOOKS, true);
		this.authors = new ModelList<Author>(this, EModelProperty.LIBRARY__AUTHORS, true);
		this.propertyChangeListeners = new LinkedList<IPropertyChangeListener>();
	}
	
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
		firePropertyChanged
		(
				EModelProperty.LIBRARY__NAME,
				EPropertyChangeEventType.SET,
				this.name,
				this.name = name
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
				EModelProperty.LIBRARY__DESCRIPTION,
				EPropertyChangeEventType.SET,
				this.description,
				this.description = description
		);
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
			case LIBRARY__DESCRIPTION:
			{
				return getDescription();
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
			case LIBRARY__DESCRIPTION:
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
