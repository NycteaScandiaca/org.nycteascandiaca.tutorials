package org.nycteascandiaca.tutorials.library.ui.editors.book;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.edit.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.edit.PropertyChangeEvent;

public class BookAuthorsListModel implements ListModel<Author>, IPropertyChangeListener
{
	private final Book book;
	
	private final List<ListDataListener> listeners;
	
	BookAuthorsListModel(Book book)
	{
		this.book = book;
		this.listeners = new LinkedList<ListDataListener>();
	}
	
	public void initialize()
	{
		book.addPropertyChangeListener(this);
	}
	
	public void dispose()
	{
		book.removePropertyChangeListener(this);
	}
	
	@Override
	public int getSize()
	{
		return book.getAuthors().size();
	}

	@Override
	public Author getElementAt(int index)
	{
		return book.getAuthors().get(index);
	}

	@Override
	public void addListDataListener(ListDataListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public void removeListDataListener(ListDataListener listener)
	{
		listeners.remove(listener);
	}
	
	private void fireContentsChanged()
	{
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0);
		listeners.forEach(current -> current.contentsChanged(event));
	}
	
	private void fireIntervalAdded(int index0, int index1)
	{
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index0, index1);
		listeners.forEach(current -> current.intervalAdded(event));
	}
	
	private void fireIntervalRemoved(int index0, int index1)
	{
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index0, index1);
		listeners.forEach(current -> current.intervalAdded(event));
	}

	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		if (event.getSource() != book)
		{
			return;
		}
		
		if (event.getProperty() == EModelProperty.BOOK__AUTHORS)
		{
			switch (event.getEventType())
			{
				case ADD:
				{
					int index = event.getIndices()[0];
					fireIntervalAdded(index, index);
					break;
				}
				case ADD_MANY:
				{
					
				}
				case REMOVE:
				{
					int index = event.getIndices()[0];
					fireIntervalRemoved(index, index);
					break;
				}
				case REMOVE_MANY:
				{
					
				}
			}
		}
	}
}
