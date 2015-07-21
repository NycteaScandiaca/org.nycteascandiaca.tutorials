package org.nycteascandiaca.tutorials.library.model.edit;

import org.nycteascandiaca.tutorials.library.model.ModelElement;

public class PropertyChangeEvent
{
	private final ModelElement source;
	
	private final EModelProperty property;
	
	private final EPropertyChangeEventType eventType;
	
	private final Object oldValue;
	
	private final Object newValue;
	
	private final int[] indices;
	
	public PropertyChangeEvent
	(
			ModelElement source,
			EModelProperty property,
			EPropertyChangeEventType eventType,
			Object oldValue,
			Object newValue,
			int... indices
	)
	{
		this.source = source;
		this.property = property;
		this.eventType = eventType;
		this.newValue = newValue;
		this.oldValue = oldValue;
		this.indices = indices;
	}
	
	public PropertyChangeEvent
	(
			ModelElement source,
			EModelProperty property,
			EPropertyChangeEventType eventType,
			Object oldValue,
			Object newValue
	)
	{
		this(source, property, eventType, oldValue, newValue, new int[0]);
	}
	
	public ModelElement getSource()
	{
		return source;
	}
	
	public EModelProperty getProperty()
	{
		return property;
	}
	
	public EPropertyChangeEventType getEventType()
	{
		return eventType;
	}
	
	public Object getOldValue()
	{
		return oldValue;
	}
	
	public Object getNewValue()
	{
		return newValue;
	}
	
	public int[] getIndices()
	{
		return indices;
	}
}
