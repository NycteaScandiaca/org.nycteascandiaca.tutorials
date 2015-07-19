package org.nycteascandiaca.tutorials.library.ui.tree;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.Library;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;

@SuppressWarnings("serial")
public class ModelTreeCellRenderer extends DefaultTreeCellRenderer
{
	ModelTreeCellRenderer()
	{
		
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		if (value == ModelTreeModel.FakeNode.AUTHORS)
		{
			this.setText("Authors");
			this.setIcon(resourceManager.getIcon(EIcon.AUTHORS_16x16));
		}
		else if (value == ModelTreeModel.FakeNode.BOOKS)
		{
			this.setText("Books");
			this.setIcon(resourceManager.getIcon(EIcon.BOOKS_16x16));
		}
		else if (value instanceof Library)
		{
			Library library = (Library)value;
			this.setIcon(resourceManager.getIcon(EIcon.LIBRARY_16x16));
			this.setText(library.getName());
		}
		else if (value instanceof Author)
		{
			Author author = (Author)value;
			this.setIcon(resourceManager.getIcon(EIcon.AUTHOR_16x16));
			this.setText(author.getFirstName() + " " + author.getLastName());
		}
		else if (value instanceof Book)
		{
			Book book = (Book)value;
			this.setIcon(resourceManager.getIcon(EIcon.BOOK_16x16));
			this.setText(book.getTitle());
		}
		
		return this;
	}
}
