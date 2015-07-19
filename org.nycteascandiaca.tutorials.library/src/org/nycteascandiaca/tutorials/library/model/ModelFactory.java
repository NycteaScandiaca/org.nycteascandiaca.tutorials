package org.nycteascandiaca.tutorials.library.model;

import java.util.UUID;


public class ModelFactory
{
	@SuppressWarnings("unchecked")
	public <T extends ModelElement> T createElement(Class<T> elementClass)
	{
		if (elementClass == Library.class)
		{
			return (T)createLibrary();
		}
		else if (elementClass == Book.class)
		{
			return (T)createBook();
		}
		else if (elementClass == Author.class)
		{
			return (T)createAuthor();
		}		
		throw new IllegalArgumentException("Not supported model element class");
	}
	
	public Library createLibrary()
	{
		return new Library(UUID.randomUUID().toString());
	}
	
	public Book createBook()
	{
		return new Book(UUID.randomUUID().toString());
	}
	
	public Author createAuthor()
	{
		return new Author(UUID.randomUUID().toString());
	}
}
