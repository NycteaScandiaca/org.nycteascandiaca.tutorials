package org.nycteascandiaca.tutorials.library.ui.editors.book;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.EModelProperty;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeListener;
import org.nycteascandiaca.tutorials.library.model.PropertyChangeEvent;

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

	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		if (event.getSource() != book)
		{
			return;
		}
		
		if (event.getProperty() == EModelProperty.BOOK__AUTHORS)
		{
			fireContentsChanged();
		}
	}
}
