package org.nycteascandiaca.tutorials.library.model;

import java.nio.file.Path;

public interface IModelManagerListener
{
	public void modelOpened(Library library, Path path);
	
	public void modelSaved(Library library, Path path);
	
	public void modelClosed(Library library);
}
