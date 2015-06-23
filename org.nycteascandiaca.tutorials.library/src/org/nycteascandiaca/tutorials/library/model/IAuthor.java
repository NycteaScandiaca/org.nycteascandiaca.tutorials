package org.nycteascandiaca.tutorials.library.model;

import java.util.List;

public interface IAuthor extends IModelElement
{	
	public String getFirstName();
	
	public void setFirstName(String firstName);
	
	public String getLastName();
	
	public void setLastName(String lastName);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public List<IBook> getBooks();
}
