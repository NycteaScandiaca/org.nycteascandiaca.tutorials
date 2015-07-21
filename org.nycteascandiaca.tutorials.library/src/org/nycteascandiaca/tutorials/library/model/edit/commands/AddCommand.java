package org.nycteascandiaca.tutorials.library.model.edit.commands;

import java.util.List;

import org.nycteascandiaca.tutorials.library.commands.ICommand;
import org.nycteascandiaca.tutorials.library.model.ModelElement;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;

public class AddCommand implements ICommand
{
	private final ModelElement container;
	private final EModelProperty property;
	private final ModelElement element;
	private int index;
	
	public AddCommand(ModelElement container, EModelProperty property, ModelElement element)
	{
		this(container, property, element, -1);
	}
	
	public AddCommand(ModelElement container, EModelProperty property, ModelElement element, int index)
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
