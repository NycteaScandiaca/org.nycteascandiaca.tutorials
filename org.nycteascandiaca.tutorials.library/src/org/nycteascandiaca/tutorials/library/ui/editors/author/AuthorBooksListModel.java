package org.nycteascandiaca.tutorials.library.ui.editors.author;

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
	
	private void fireContentsChanged()
	{
		ListDataEvent event = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0);
		listeners.forEach(current -> current.contentsChanged(event));
	}

	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		if (event.getSource() != author)
		{
			return;
		}
		
		if (event.getProperty() == EModelProperty.AUTHOR__BOOKS)
		{
			fireContentsChanged();
		}
	}
}
