package org.nycteascandiaca.tutorials.library.model;


public interface IModelFactory
{	
	public <T extends IModelElement> T createElement(Class<T> elementClass);
	
	public ILibrary createLibrary();
	
	public IBook createBook();
	
	public IAuthor createAuthor();
}
