package org.nycteascandiaca.tutorials.library.ui.editors.author;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.edit.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.edit.PropertyChangeEvent;

class AuthorBooksListModel implements ListModel<Book>, IPropertyChangeListener
{
	private final Author author;
	
	private final List<ListDataListener> listeners;
	
	AuthorBooksListModel(Author author)
	{
		this.author = author;
		this.listeners = new LinkedList<ListDataListener>();
	}
	
	public void initialize()
	{
		author.addPropertyChangeListener(this);
	}
	
	public void dispose()
	{
		author.removePropertyChangeListener(this);
	}
	
	@Override
	public int getSize()
	{
		return author.getBooks().size();
	}

	@Override
	public Book getElementAt(int index)
	{
		return author.getBooks().get(index);
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
	
	private void fireContentsChanged(int index0, int index1)
	{
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index0, index1);
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
	@SuppressWarnings("incomplete-switch")
	public void propertyChange(PropertyChangeEvent event)
	{
		switch(event.getProperty())
		{
			case BOOK__TITLE:
			case BOOK__PUBLICATION_DATE:
			case BOOK__PAGES:
			{
				int index = author.getBooks().indexOf(event.getSource());
				if (index >= 0)
				{
					fireContentsChanged(index, index);
				}
				break;
			}
			case AUTHOR__BOOKS:
			{
				if (event.getSource() == author)
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
							// TODO Improve me
							for (int index : event.getIndices())
							{
								fireIntervalAdded(index, index);
							}
						}
						case REMOVE:
						{
							int index = event.getIndices()[0];
							fireIntervalRemoved(index, index);
							break;
						}
						case REMOVE_MANY:
						{
							// TODO Improve me
							for (int index : event.getIndices())
							{
								fireIntervalRemoved(index, index);
							}
						}
					}
				}
				break;
			}
		}
	}
}
