package org.nycteascandiaca.tutorials.library.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.EPropertyChangeEventType;
import org.nycteascandiaca.tutorials.library.model.edit.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.edit.PropertyChangeEvent;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class ModelElement
{
    @XmlID
    @XmlAttribute(name = "Id")
	private String id;
	
    @XmlIDREF
    @XmlElement(name = "Owner")
	private ModelElement owner;
	
	ModelElement(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		firePropertyChanged
		(
				EModelProperty.MODEL_ELEMENT__ID,
				EPropertyChangeEventType.SET,
				this.id,
				this.id = id
		);
	}
	
	public ModelElement getOwner()
	{
		return owner;
	}
	
	public void setOwner(ModelElement owner)
	{
		firePropertyChanged
		(
				EModelProperty.MODEL_ELEMENT__OWNER,
				EPropertyChangeEventType.SET,
				this.owner,
				this.owner = owner
		);
	}
	
	public Library getRoot()
	{
		ModelElement element = this;
		do
		{
			if (element instanceof Library)
			{
				element = (Library)element;
				break;
			}
			element = getOwner();
		}
		while(element != null);
		
		return (Library)element;
	}
	
	public void addPropertyChangeListener(IPropertyChangeListener listener)
	{
		Library library = (Library)getRoot();
		if (library != null)
		{
			library.addPropertyChangeListener(listener);
		}
	}
	
	public void removePropertyChangeListener(IPropertyChangeListener listener)
	{
		Library library = (Library)getRoot();
		if (library != null)
		{
			library.removePropertyChangeListener(listener);
		}
	}
	
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
				setOwner((ModelElement)value);
				break;
			}
			default:
			{
				throw new IllegalArgumentException("Not supported property");
			}
		}
	}
	
	protected void firePropertyChanged(EModelProperty property, EPropertyChangeEventType eventType, Object oldValue, Object newValue)
	{
		firePropertyChanged(new PropertyChangeEvent(this, property, eventType, oldValue, newValue));
	}
	
	protected void firePropertyChanged(PropertyChangeEvent event)
	{
		if (event.getOldValue() == event.getNewValue())
		{
			return;
		}
		
		Library library = (Library)getRoot();
		if (library != null)
		{
			library.firePropertyChanged(event);
		}
	}
}
