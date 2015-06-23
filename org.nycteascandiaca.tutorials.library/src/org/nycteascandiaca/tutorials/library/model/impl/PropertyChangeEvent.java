package org.nycteascandiaca.tutorials.library.model.impl;

import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeEvent;

class PropertyChangeEvent implements IPropertyChangeEvent
{
	private final ModelElement source;
	
	private final EModelProperty property;
	
	private final Object oldValue;
	
	private final Object newValue;
	
	PropertyChangeEvent(ModelElement source, EModelProperty property, Object oldValue, Object newValue)
	{
		this.source = source;
		this.property = property;
		this.newValue = newValue;
		this.oldValue = oldValue;
	}
	
	@Override
	public ModelElement getSource()
	{
		return source;
	}

	@Override
	public EModelProperty getProperty()
	{
		return property;
	}

	@Override
	public Object getOldValue()
	{
		return oldValue;
	}
	
	@Override
	public Object getNewValue()
	{
		return newValue;
	}
}
