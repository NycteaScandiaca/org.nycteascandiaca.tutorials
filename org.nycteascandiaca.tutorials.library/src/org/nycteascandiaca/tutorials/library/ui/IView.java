package org.nycteascandiaca.tutorials.library.ui;

public interface IView<T>
{
	public EView getType();
	
	public T getInput();
	
	public void setInput(T input);
}
