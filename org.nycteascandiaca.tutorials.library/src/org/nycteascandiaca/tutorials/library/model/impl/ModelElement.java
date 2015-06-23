package org.nycteascandiaca.tutorials.library.model.impl;

import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.ILibrary;
import org.nycteascandiaca.tutorials.library.model.IModelElement;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeEvent;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeListener;

public abstract class ModelElement implements IModelElement
{
	private String id;
	
	private IModelElement owner;
	
	ModelElement()
	{
		
	}
	
	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public void setId(String id)
	{
		firePropertyChanged(EModelProperty.MODEL_ELEMENT__ID, this.id, this.id = id);
	}
	
	@Override
	public IModelElement getOwner()
	{
		return owner;
	}

	@Override
	public void setOwner(IModelElement owner)
	{
		firePropertyChanged(EModelProperty.MODEL_ELEMENT__ID, this.owner, this.owner = owner);
	}
	
	@Override
	public ILibrary getRoot()
	{
		IModelElement element = this;
		do
		{
			if (element instanceof ILibrary)
			{
				element = (ILibrary)element;
				break;
			}
			element = getOwner();
		}
		while(element != null);
		
		return (ILibrary)element;
	}
	
	@Override
	public void addPropertyChangeListener(IPropertyChangeListener listener)
	{
		Library library = (Library)getRoot();
		if (library != null)
		{
			library.addPropertyChangeListener(listener);
		}
	}
	
	@Override
	public void removePropertyChangeListener(IPropertyChangeListener listener)
	{
		Library library = (Library)getRoot();
		if (library != null)
		{
			library.removePropertyChangeListener(listener);
		}
	}
	
	@Override
	public Object getPropertyValue(EModelProperty property)
	{
		switch(property)
		{
			case MODEL_ELEMENT__ID:
			{
				return getId();
			}
			case MODEL_ELEMENT__OWNER:
			{
				return getOwner();
			}
			default:
			{
				throw new IllegalArgumentException("Not supported property");
			}
		}
	}
	
	@Override
	public void setPropertyValue(EModelProperty property, Object value)
	{
		switch(property)
		{
			case MODEL_ELEMENT__ID:
			{
				setId((String)value);
				break;
			}
			case MODEL_ELEMENT__OWNER:
			{
				setOwner((IModelElement)value);
				break;
			}
			default:
			{
				throw new IllegalArgumentException("Not supported property");
			}
		}
	}
	
	protected void firePropertyChanged(EModelProperty property, Object oldValue, Object newValue)
	{
		firePropertyChanged(new PropertyChangeEvent(this, property, oldValue, newValue));
	}
	
	protected void firePropertyChanged(IPropertyChangeEvent event)
	{
		Library library = (Library)getRoot();
		if (library != null)
		{
			library.firePropertyChanged(event);
		}
	}
}
