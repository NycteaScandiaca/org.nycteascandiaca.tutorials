package org.nycteascandiaca.tutorials.library.model.edit.commands;

import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.ModelElement;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;

public class SetCommand implements ICommand
{
	private final ModelElement element;
	private final EModelProperty property;
	private final Object newValue;
	private Object oldValue;
	
	public SetCommand(ModelElement element, EModelProperty property, Object newValue)
	{
		this.element = element;
		this.property = property;
		this.newValue = newValue;
	}

	@Override
	public void execute()
	{
		oldValue = element.getPropertyValue(property);
		
		redo();
	}

	@Override
	public void undo()
	{
		element.setPropertyValue(property, oldValue);
	}

	@Override
	public void redo()
	{
		element.setPropertyValue(property, newValue);
	}

}
