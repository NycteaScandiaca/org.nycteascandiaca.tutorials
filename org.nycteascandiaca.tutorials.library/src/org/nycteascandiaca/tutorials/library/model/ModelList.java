package org.nycteascandiaca.tutorials.library.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ModelList<T extends ModelElement> implements List<T>
{
	private final ModelElement source;
	
	private final EModelProperty property;
	
	private final List<T> list;
	
	private final boolean container;
	
	ModelList(ModelElement source, EModelProperty property, boolean container)
	{
		this(source, property, container, new ArrayList<T>());
	}
	
	ModelList(ModelElement source, EModelProperty property, boolean container, List<T> list)
	{
		this.source = source;
		this.property = property;
		this.container = container;
		this.list = list;
	}
	
	public Object getSource()
	{
		return source;
	}
	
	public EModelProperty getProperty()
	{
		return property;
	}
	
	public boolean isContainer()
	{
		return container;
	}

	@Override
	public int size()
	{
		return list.size();
	}

	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		return list.contains(o);
	}

	@Override
	public Iterator<T> iterator()
	{
		return Collections.unmodifiableList(list).iterator();
	}

	@Override
	public Object[] toArray()
	{
		return list.toArray();
	}
	
	@Override
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a)
	{
		return list.toArray(a);
	}

	@Override
	public boolean add(T e)
	{
		boolean isChanged = list.add(e);
		if (isChanged)
		{
			if (container)
			{
				e.setOwner(source);
			}
			source.firePropertyChanged(property, null, null);
		}
		return isChanged;
	}

	@Override
	public boolean remove(Object o)
	{
		boolean isChanged = list.remove(o);
		if (isChanged)
		{
			source.firePropertyChanged(property, null, null);
		}
		return isChanged;
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		boolean isChanged = list.addAll(c);
		if (isChanged)
		{
			if (container)
			{
				c.forEach(current -> current.setOwner(source));
			}
			source.firePropertyChanged(property, null, null);
		}
		return isChanged;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c)
	{
		boolean isChanged = list.addAll(index, c);
		if (isChanged)
		{
			if (container)
			{
				c.forEach(current -> current.setOwner(source));
			}
			source.firePropertyChanged(property, null, null);
		}
		return isChanged;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		boolean isChanged = list.removeAll(c);
		if (isChanged)
		{
			source.firePropertyChanged(property, null, null);
		}
		return isChanged;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		boolean isChanged = list.retainAll(c);
		if (isChanged)
		{
			source.firePropertyChanged(property, null, null);
		}
		return isChanged;
	}

	@Override
	public void clear()
	{
		boolean isChanged = !list.isEmpty();
		list.clear();
		if (isChanged)
		{
			source.firePropertyChanged(property, null, null);
		}
	}

	@Override
	public T get(int index)
	{
		return list.get(index);
	}

	@Override
	public T set(int index, T element)
	{
		T result = list.set(index, element);
		source.firePropertyChanged(property, null, null);
		return result;
	}

	@Override
	public void add(int index, T element)
	{
		list.add(index, element);
		source.firePropertyChanged(property, null, null);
	}

	@Override
	public T remove(int index)
	{
		T result = list.remove(index);
		source.firePropertyChanged(property, null, null);
		return result;
	}

	@Override
	public int indexOf(Object o)
	{
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o)
	{
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator()
	{
		return Collections.unmodifiableList(list).listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index)
	{
		return Collections.unmodifiableList(list).listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex)
	{
		return list.subList(fromIndex, toIndex);
	}	
}
