package org.nycteascandiaca.tutorials.library.model;

import java.time.LocalDate;
import java.util.List;

public interface IBook extends IModelElement
{
	public String getTitle();
	
	public void setTitle(String title);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public LocalDate getPublicationDate();
	
	public void setPublicationDate(LocalDate publicationDate);
	
	public EBookCategory getCategory();
	
	public void setCategory(EBookCategory category);
	
	public int getPages();
	
	public void setPages(int pages);
	
	public List<IAuthor> getAuthors();
}
