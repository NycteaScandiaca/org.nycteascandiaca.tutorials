package org.nycteascandiaca.tutorials.library.ui.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.nycteascandiaca.tutorials.library.model.IAuthor;
import org.nycteascandiaca.tutorials.library.model.IBook;
import org.nycteascandiaca.tutorials.library.model.ILibrary;
import org.nycteascandiaca.tutorials.library.model.IModelElement;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeEvent;
import org.nycteascandiaca.tutorials.library.model.IPropertyChangeListener;

public class ModelTreeModel implements TreeModel, IPropertyChangeListener
{
	public enum FakeNode
	{
		AUTHORS,
		BOOKS
	}
	
	private static final List<FakeNode> LIBRARY_CHILDREN = Collections.unmodifiableList(Arrays.asList
	(
			FakeNode.AUTHORS,
			FakeNode.BOOKS
	));
	
	private final ILibrary library;
	
	private final List<TreeModelListener> listeners;
	
	ModelTreeModel(ILibrary library)
	{
		this.library = library;
		this.library.addPropertyChangeListener(this);
		this.listeners = new LinkedList<TreeModelListener>();
	}

	@Override
	public Object getRoot()
	{
		return library;
	}

	@Override
	public boolean isLeaf(Object node)
	{
		if (node == FakeNode.AUTHORS)
		{
			return library.getAuthors().isEmpty();
		}
		else if (node == FakeNode.BOOKS)
		{
			return library.getBooks().isEmpty();
		}
		else if (node instanceof ILibrary)
		{
			return LIBRARY_CHILDREN.isEmpty();
		}
		return true;
	}
	
	@Override
	public int getChildCount(Object parent)
	{
		if (parent == FakeNode.AUTHORS)
		{
			return library.getAuthors().size();
		}
		else if (parent == FakeNode.BOOKS)
		{
			return library.getBooks().size();
		}
		else if (parent instanceof ILibrary)
		{
			return LIBRARY_CHILDREN.size();
		}
		return 0;
	}
	
	@Override
	public Object getChild(Object parent, int index)
	{
		if (parent == FakeNode.AUTHORS)
		{
			return library.getAuthors().get(index);
		}
		else if (parent == FakeNode.BOOKS)
		{
			return library.getBooks().get(index);
		}
		else if (parent instanceof ILibrary)
		{
			return LIBRARY_CHILDREN.get(index);
		}
		throw new RuntimeException();
	}
	
	@Override
	public int getIndexOfChild(Object parent, Object child)
	{
		if (parent == FakeNode.AUTHORS)
		{
			return library.getAuthors().indexOf(child);
		}
		else if (parent == FakeNode.BOOKS)
		{
			return library.getBooks().indexOf(child);
		}
		else if (parent instanceof ILibrary)
		{
			return LIBRARY_CHILDREN.indexOf(child);
		}
		return 0;
	}
	
	@Override
	public void valueForPathChanged(TreePath path, Object newValue)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTreeModelListener(TreeModelListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener listener)
	{
		listeners.remove(listener);
	}
	
	
	@Override
	@SuppressWarnings("incomplete-switch")
	public void propertyChange(IPropertyChangeEvent event)
	{
		IModelElement source = event.getSource();
		switch(event.getProperty())
		{
			case LIBRARY__NAME:
			case AUTHOR__FIRST_NAME:
			case AUTHOR__LAST_NAME:
			{
				TreePath path = createPath(library, source);
				fireTreeNodesChanged(path);
				break;
			}
			case LIBRARY__AUTHORS:
			case LIBRARY__BOOKS:
			case AUTHOR__BOOKS:
			case BOOK__AUTHORS:
			{
				TreePath path = createPath(library, source);
				fireTreeStructureChanged(path);
				break;
			}
		}
	}
	
	private void fireTreeNodesChanged(TreePath path)
	{
		TreeModelEvent event = new TreeModelEvent(this, path);
		listeners.forEach(current -> current.treeNodesChanged(event));
	}
	
	private void fireTreeStructureChanged(TreePath path)
	{
		TreeModelEvent event = new TreeModelEvent(this, path);
		listeners.forEach(current -> current.treeStructureChanged(event));
	}
	
	void dispose()
	{
		library.removePropertyChangeListener(this);
	}

	public static TreePath createPath(ILibrary library, Object node)
	{
		if (node == FakeNode.AUTHORS)
		{
			return new TreePath(new Object[]
			{
					library,
					FakeNode.AUTHORS
			});
		}
		else if (node == FakeNode.BOOKS)
		{
			return new TreePath(new Object[]
			{
					library,
					FakeNode.BOOKS
			});
		}
		else if (node instanceof ILibrary)
		{
			return new TreePath(node);
		}
		else if (node instanceof IAuthor)
		{
			return new TreePath(new Object[]
			{
					library,
					FakeNode.AUTHORS,
					node
			});
		}
		else if (node instanceof IBook)
		{
			return new TreePath(new Object[]
			{
					library,
					FakeNode.BOOKS,
					node
			});
		}
		throw new IllegalArgumentException("Not supported node");
	}
}
