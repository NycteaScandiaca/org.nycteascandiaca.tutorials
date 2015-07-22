package org.nycteascandiaca.tutorials.library.ui;

import org.nycteascandiaca.tutorials.library.model.ModelElement;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;

public class UIUtils
{
	public static String toString(ModelElement element, EModelProperty property)
	{
		Object propertyValue = element.getPropertyValue(property);
		return toString(propertyValue);
	}
	
	public static String toString(Object object)
	{
		return object == null ? "" : object.toString();
	}
}
