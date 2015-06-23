package org.nycteascandiaca.tutorials.library.model.commands;

import java.util.List;

import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IModelElement;

public class AddCommand implements ICommand
{
	private final IModelElement container;
	private final EModelProperty property;
	private final IModelElement element;
	private int index;
	
	public AddCommand(IModelElement container, EModelProperty property, IModelElement element)
	{
		this(container, property, element, -1);
	}
	
	public AddCommand(IModelElement container, EModelProperty property, IModelElement element, int index)
	{
		this.container = container;
		this.property = property;
		this.element = element;
		this.index = index;
	}
	
	@Override
	public void execute()
	{
		List<?> list = (List<?>)container.getPropertyValue(property);
		if (index < 0)
		{
			index = list.size();
		}
		redo();
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void undo()
	{
		List list = (List<?>)container.getPropertyValue(property);
		list.remove(index);
	}
	
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void redo()
	{
		List list = (List<?>)container.getPropertyValue(property);
		list.add(index, element);
	}

}
