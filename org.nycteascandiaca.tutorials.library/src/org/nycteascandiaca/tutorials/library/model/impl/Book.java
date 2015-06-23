package org.nycteascandiaca.tutorials.library.model.impl;

import java.time.LocalDate;
import java.util.List;

import org.nycteascandiaca.tutorials.library.model.EBookCategory;
import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IAuthor;
import org.nycteascandiaca.tutorials.library.model.IBook;

public class Book extends ModelElement implements IBook
{
	private String title;
	
	private String description;
	
	private LocalDate publicationDate;
	
	private EBookCategory category;
	
	private int pages;
	
	private final List<IAuthor> authors;
	
	Book()
	{
		authors = new ModelList<IAuthor>(this, EModelProperty.BOOK__AUTHORS, false);
	}
	
	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public void setTitle(String title)
	{
		firePropertyChanged(EModelProperty.BOOK__TITLE, this.title, this.title = title);
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public void setDescription(String description)
	{
		firePropertyChanged(EModelProperty.BOOK__DESCRIPTION, this.description, this.description = description);
	}

	@Override
	public LocalDate getPublicationDate()
	{
		return publicationDate;
	}

	@Override
	public void setPublicationDate(LocalDate publicationDate)
	{
		firePropertyChanged(EModelProperty.BOOK__PUBLICATION_DATE, this.publicationDate, this.publicationDate = publicationDate);
	}

	@Override
	public EBookCategory getCategory()
	{
		return category;
	}

	@Override
	public void setCategory(EBookCategory category)
	{
		firePropertyChanged(EModelProperty.BOOK__CATEGORY, this.category, this.category = category);
	}

	@Override
	public int getPages()
	{
		return pages;
	}

	@Override
	public void setPages(int pages)
	{
		firePropertyChanged(EModelProperty.BOOK__PAGES, this.pages, this.pages = pages);
	}

	@Override
	public List<IAuthor> getAuthors()
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
