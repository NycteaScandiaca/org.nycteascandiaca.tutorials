package org.nycteascandiaca.tutorials.library.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Selection
{
	public static final Selection EMPTY = new Selection();
	
	private final List<Object> elements;
	
	public Selection(Object... elements)
	{
		this.elements = Collections.unmodifiableList(Arrays.asList(elements));
	}
	
	public Selection(List<?> elements)
	{
		this.elements = Collections.unmodifiableList(elements);
	}
	
	public List<Object> getElements()
	{
		return elements;
	}
}
