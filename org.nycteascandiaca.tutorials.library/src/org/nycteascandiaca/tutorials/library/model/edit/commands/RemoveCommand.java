package org.nycteascandiaca.tutorials.library.model.edit.commands;

import java.util.List;

import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.ModelElement;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;

public class RemoveCommand implements ICommand
{
	private final ModelElement container;
	private final EModelProperty property;
	private ModelElement element;
	private int index;

	public RemoveCommand(ModelElement container, EModelProperty property, ModelElement element)
	{
		this(container, property, -1);
		this.element = element;
	}
	
	public RemoveCommand(ModelElement container, EModelProperty property, int index)
	{
		this.container = container;
		this.property = property;
		this.index = index;
	}
	
	@Override
	public void execute()
	{
		List<?> list = (List<?>)container.getPropertyValue(property);
		if (index < 0)
		{	
			index = list.indexOf(element);
		}
		else if (element == null)
		{
			element = (ModelElement)list.get(index);
		}
		redo();
	}
	
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void undo()
	{
		List list = (List<?>)container.getPropertyValue(property);
		list.add(index, element);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void redo()
	{
		List list = (List<?>)container.getPropertyValue(property);
		list.remove(index);
	}
}
