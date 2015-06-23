package org.nycteascandiaca.tutorials.library.model;

public interface IPropertyChangeEvent
{
	IModelElement getSource();
	
	public EModelProperty getProperty();
	
	public Object getOldValue();
	
	public Object getNewValue();
}
