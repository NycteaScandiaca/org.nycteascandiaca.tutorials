package org.nycteascandiaca.tutorials.library.model;

import java.time.LocalDate;
import java.util.List;

public class Book extends ModelElement
{
	private String title;
	
	private String description;
	
	private LocalDate publicationDate;
	
	private EBookCategory category;
	
	private int pages;
	
	private final List<Author> authors;
	
	Book(String id)
	{
		super(id);
		
		authors = new ModelList<Author>(this, EModelProperty.BOOK__AUTHORS, false);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		firePropertyChanged(EModelProperty.BOOK__TITLE, this.title, this.title = title);
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		firePropertyChanged(EModelProperty.BOOK__DESCRIPTION, this.description, this.description = description);
	}
	
	public LocalDate getPublicationDate()
	{
		return publicationDate;
	}
	
	public void setPublicationDate(LocalDate publicationDate)
	{
		firePropertyChanged(EModelProperty.BOOK__PUBLICATION_DATE, this.publicationDate, this.publicationDate = publicationDate);
	}
	
	public EBookCategory getCategory()
	{
		return category;
	}
	
	public void setCategory(EBookCategory category)
	{
		firePropertyChanged(EModelProperty.BOOK__CATEGORY, this.category, this.category = category);
	}
	
	public int getPages()
	{
		return pages;
	}
	
	public void setPages(int pages)
	{
		firePropertyChanged(EModelProperty.BOOK__PAGES, this.pages, this.pages = pages);
	}
	
	public List<Author> getAuthors()
	{
		return authors;
	}

	@Override
	public Object getPropertyValue(EModelProperty property)
	{
		switch(property)
		{
			case BOOK__TITLE:
			{
				return getTitle();
			}
			case BOOK__CATEGORY:
			{
				return getCategory();
			}
			case BOOK__PAGES:
			{
				return getPages();
			}
			case BOOK__PUBLICATION_DATE:
			{
				return getPublicationDate();
			}
			case BOOK__DESCRIPTION:
			{
				return getDescription();
			}
			case BOOK__AUTHORS:
			{
				return getAuthors();
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
			case BOOK__TITLE:
			{
				setTitle((String)value);
				break;
			}
			case BOOK__CATEGORY:
			{
				setCategory((EBookCategory)value);
				break;
			}
			case BOOK__PAGES:
			{
				setPages((int)value);
				break;
			}
			case BOOK__PUBLICATION_DATE:
			{
				setPublicationDate((LocalDate)value);
				break;
			}
			case BOOK__DESCRIPTION:
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
