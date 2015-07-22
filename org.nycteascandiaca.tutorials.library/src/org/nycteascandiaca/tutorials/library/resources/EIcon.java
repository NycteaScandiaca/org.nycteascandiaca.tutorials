package org.nycteascandiaca.tutorials.library.resources;

public enum EIcon
{
	NEW_MODEL_16x16 ("NewModel16x16.png"),
	NEW_MODEL_32x32 ("NewModel32x32.png"),
	
	OPEN_MODEL_16x16 ("OpenModel16x16.png"),
	OPEN_MODEL_32x32 ("OpenModel32x32.png"),
	
	CLOSE_MODEL_16x16 ("CloseModel16x16.png"),
	
	SAVE_MODEL_16x16 ("SaveModel16x16.png"),
	SAVE_MODEL_32x32 ("SaveModel32x32.png"),
	
	SAVE_AS_MODEL_16x16 ("SaveModel16x16.png"),
	
	EXIT_16x16 ("Exit16x16.png"),
	
	UNDO_16x16 ("Undo16x16.png"),
	UNDO_32x32 ("Undo32x32.png"),
	
	REDO_16x16 ("Redo16x16.png"),
	REDO_32x32 ("Redo32x32.png"),
	
	LIBRARY_16x16 ("Library16x16.png"),
	LIBRARY_32x32 ("Library32x32.png"),
	LIBRARY_48x48 ("Library48x48.png"),
	
	AUTHORS_16x16 ("Authors16x16.png"),
	AUTHORS_32x32 ("Authors32x32.png"),
	
	AUTHOR_16x16 ("Author16x16.png"),
	AUTHOR_32x32 ("Author32x32.png"),
	AUTHOR_48x48 ("Author48x48.png"),
	
	BOOK_16x16 ("Book16x16.png"),
	BOOK_32x32 ("Book32x32.png"),
	BOOK_48x48 ("Book48x48.png"),
	
	BOOKS_16x16 ("Books16x16.png"),
	BOOKS_32x32 ("Books32x32.png"),
	
	DELETE_16x16 ("Delete16x16.png"),
	DELETE_32x32 ("Delete32x32.png");
	
	private final String resource;
	
	private EIcon(String resource)
	{
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
