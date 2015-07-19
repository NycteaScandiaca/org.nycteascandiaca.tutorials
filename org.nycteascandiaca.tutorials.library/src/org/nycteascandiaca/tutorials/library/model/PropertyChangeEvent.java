package org.nycteascandiaca.tutorials.library.model;

public class PropertyChangeEvent
{
	private final ModelElement source;
	
	private final EModelProperty property;
	
	private final Object oldValue;
	
	private final Object newValue;
	
	public PropertyChangeEvent(ModelElement source, EModelProperty property, Object oldValue, Object newValue)
	{
		this.source = source;
		this.property = property;
		this.newValue = newValue;
		this.oldValue = oldValue;
	}
	
	public ModelElement getSource()
	{
		return source;
	}
	
	public EModelProperty getProperty()
	{
		return property;
	}
	
	public Object getOldValue()
	{
		return oldValue;
	}
	
	public Object getNewValue()
	{
		return newValue;
	}
}
