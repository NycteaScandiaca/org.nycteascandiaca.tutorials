package org.nycteascandiaca.tutorials.library.model.impl;

import org.nycteascandiaca.tutorials.library.model.IAuthor;
import org.nycteascandiaca.tutorials.library.model.IBook;
import org.nycteascandiaca.tutorials.library.model.ILibrary;
import org.nycteascandiaca.tutorials.library.model.IModelElement;
import org.nycteascandiaca.tutorials.library.model.IModelFactory;

public class ModelFactory implements IModelFactory
{	
	@Override
	@SuppressWarnings("unchecked")
	public <T extends IModelElement> T createElement(Class<T> elementClass)
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

	@Override
	public ILibrary createLibrary()
	{
		return new Library();
	}

	@Override
	public IBook createBook()
	{
		return new Book();
	}

	@Override
	public IAuthor createAuthor()
	{
		return new Author();
	}
}
