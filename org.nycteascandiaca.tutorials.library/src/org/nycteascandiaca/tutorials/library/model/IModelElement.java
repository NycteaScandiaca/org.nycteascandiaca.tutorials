package org.nycteascandiaca.tutorials.library.model;

public interface IModelElement
{
	public String getId();
	
	public void setId(String id);
	
	public IModelElement getOwner();
	
	public ILibrary getRoot();
	
	public void setOwner(IModelElement owner);
	
	public void addPropertyChangeListener(IPropertyChangeListener listener);
	
	public void removePropertyChangeListener(IPropertyChangeListener listener);
	
	public Object getPropertyValue(EModelProperty property);
	
	public void setPropertyValue(EModelProperty property, Object value);
}
