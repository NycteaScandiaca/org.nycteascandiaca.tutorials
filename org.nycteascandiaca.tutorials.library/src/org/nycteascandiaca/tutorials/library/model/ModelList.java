package org.nycteascandiaca.tutorials.library.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.EPropertyChangeEventType;
import org.nycteascandiaca.tutorials.library.model.edit.PropertyChangeEvent;

class ModelList<T extends ModelElement> implements List<T>
{
	private final ModelElement source;
	
	private final EModelProperty property;
	
	private final ArrayList<T> list;
	
	private final boolean container;
		
	ModelList(ModelElement source, EModelProperty property, boolean container)
	{
		this.source = source;
		this.property = property;
		this.container = container;
		this.list = new ArrayList<T>();
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
		if (container)
		{
			e.setOwner(source);
		}
		
		list.add(e);
		PropertyChangeEvent event = new PropertyChangeEvent
		(
				source,
				property,
				EPropertyChangeEventType.ADD,
				null,
				e,
				list.size() - 1
		);
		source.firePropertyChanged(event);
		return true;
	}
	
	@Override
	public boolean remove(Object o)
	{
		int indexOf = list.indexOf(o);
		if (indexOf < 0)
		{
			return false;
		}
		
		list.remove(indexOf);
		
		if (container)
		{
			((ModelElement)o).setOwner(null);
		}
		
		PropertyChangeEvent event = new PropertyChangeEvent
		(
				source,
				property,
				EPropertyChangeEventType.REMOVE,
				o,
				null,
				indexOf
		);
		source.firePropertyChanged(event);
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		if (container)
		{
			c.forEach(current -> current.setOwner(source));
		}
		
		boolean isChanged = list.addAll(c);
		if (isChanged)
		{
			int[] indices = new int[c.size()];
			for (int i = 0; i < indices.length; i++)
			{
				indices[i] = c.size() + i;
			}
			
			PropertyChangeEvent event = new PropertyChangeEvent
			(
					source,
					property,
					EPropertyChangeEventType.ADD_MANY,
					null,
					c.toArray(),
					indices
			);
			source.firePropertyChanged(event);
		}
		return isChanged;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c)
	{
		if (container)
		{
			c.forEach(current -> current.setOwner(source));
		}
		
		boolean isChanged = list.addAll(index, c);
		if (isChanged)
		{
			int[] indices = new int[c.size()];
			for (int i = 0; i < indices.length; i++)
			{
				indices[i] = index + i;
			}
			
			PropertyChangeEvent event = new PropertyChangeEvent
			(
					source,
					property,
					EPropertyChangeEventType.REMOVE,
					null,
					c.toArray(),
					indices
			);
			source.firePropertyChanged(event);
		}
		return isChanged;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		int[] indices = new int[c.size()];
		int length = 0;
		for (Object current : c)
		{
			int index = list.indexOf(current);
			if (index < 0)
			{
				continue;
			}
			
			indices[length] = index;
			length++;
			T element = list.remove(index);
			if (container)
			{
				element.setOwner(null);
			}
		}
		
		if (length == 0)
		{
			return false;
		}
		
		indices = Arrays.copyOfRange(indices, 0, length);
		
		PropertyChangeEvent event = new PropertyChangeEvent
		(
				source,
				property,
				EPropertyChangeEventType.REMOVE_MANY,
				c.toArray(),
				null,
				indices
		);
		source.firePropertyChanged(event);
		
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		throw new UnsupportedOperationException("]:>");
	}

	@Override
	public void clear()
	{
		int size = list.size();
		if (size == 0)
		{
			return;
		}
		
		int[] indices = new int[size];
		for (int i = 0; i < size; i++)
		{
			indices[i] = i;
		}
		
		ModelElement[] oldValues = list.toArray(new ModelElement[list.size()]);
		list.clear();
		
		if (container)
		{
			for (ModelElement element : oldValues)
			{
				element.setOwner(null);
			}
		}
		
		PropertyChangeEvent event = new PropertyChangeEvent
		(
				source,
				property,
				EPropertyChangeEventType.REMOVE_MANY,
				oldValues,
				null,
				indices
		);
		source.firePropertyChanged(event);
	}

	@Override
	public T get(int index)
	{
		return list.get(index);
	}

	@Override
	public T set(int index, T element)
	{
		throw new UnsupportedOperationException("]:>");
	}

	@Override
	public void add(int index, T element)
	{
		if (container)
		{
			element.setOwner(source);
		}
		
		list.add(index, element);
		
		PropertyChangeEvent event = new PropertyChangeEvent
		(
				source,
				property,
				EPropertyChangeEventType.ADD,
				null,
				element,
				index
		);
		source.firePropertyChanged(event);
	}

	@Override
	public T remove(int index)
	{
		T result = list.get(index);
		if (container)
		{
			result.setOwner(null);
		}
		
		list.remove(index);
		
		PropertyChangeEvent event = new PropertyChangeEvent
		(
				source,
				property,
				EPropertyChangeEventType.REMOVE,
				result,
				null,
				index
		);
		source.firePropertyChanged(event);
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
