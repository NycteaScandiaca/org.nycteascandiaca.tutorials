package org.nycteascandiaca.tutorials.library.model;

import java.util.List;

public interface ILibrary extends IModelElement
{
	public String getName();
	
	public void setName(String name);
	
	public List<IBook> getBooks();
	
	public List<IAuthor> getAuthors();
}
