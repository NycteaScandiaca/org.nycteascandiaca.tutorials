package org.nycteascandiaca.tutorials.library.model.commands;

import java.util.List;

import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IModelElement;

public class RemoveCommand implements ICommand
{
	private final IModelElement container;
	private final EModelProperty property;
	private IModelElement element;
	private int index;

	public RemoveCommand(IModelElement container, EModelProperty property, IModelElement element)
	{
		this(container, property, -1);
		this.element = element;
	}
	
	public RemoveCommand(IModelElement container, EModelProperty property, int index)
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
			element = (IModelElement)list.get(index);
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
