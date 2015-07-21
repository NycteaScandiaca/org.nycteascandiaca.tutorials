package org.nycteascandiaca.tutorials.library.model;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.EPropertyChangeEventType;
import org.nycteascandiaca.tutorials.library.model.utils.LocalDateXMLAdapter;

@XmlRootElement(name = "Book")
public class Book extends ModelElement
{
	@XmlElement(name = "Title")
	private String title;
	
	@XmlElement(name = "Description")
	private String description;
	
	@XmlJavaTypeAdapter(LocalDateXMLAdapter.class)
	@XmlElement(name = "PublicationDate")
	private LocalDate publicationDate;
	
	@XmlElement(name="Category", required = true)
	private EBookCategory category;
	
	@XmlElement(name = "pages")
	private int pages;
	
	@XmlIDREF
	@XmlElementWrapper(name="Authors")
    @XmlElement(name="AuthorId")
	private final List<Author> authors;
	
	Book()
	{
		super(null);
		
		authors = new ModelList<Author>(this, EModelProperty.BOOK__AUTHORS, false);
	}
	
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
		firePropertyChanged
		(
				EModelProperty.BOOK__TITLE,
				EPropertyChangeEventType.SET,
				this.title,
				this.title = title
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
				EModelProperty.BOOK__DESCRIPTION,
				EPropertyChangeEventType.SET,
				this.description,
				this.description = description
		);
	}
	
	public LocalDate getPublicationDate()
	{
		return publicationDate;
	}
	
	public void setPublicationDate(LocalDate publicationDate)
	{
		firePropertyChanged
		(
				EModelProperty.BOOK__PUBLICATION_DATE,
				EPropertyChangeEventType.SET,
				this.publicationDate,
				this.publicationDate = publicationDate
		);
	}
	
	public EBookCategory getCategory()
	{
		return category;
	}
	
	public void setCategory(EBookCategory category)
	{
		firePropertyChanged
		(
				EModelProperty.BOOK__CATEGORY,
				EPropertyChangeEventType.SET,
				this.category,
				this.category = category
		);
	}
	
	public int getPages()
	{
		return pages;
	}
	
	public void setPages(int pages)
	{
		firePropertyChanged
		(
				EModelProperty.BOOK__PAGES,
				EPropertyChangeEventType.SET,
				this.pages,
				this.pages = pages
		);
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
