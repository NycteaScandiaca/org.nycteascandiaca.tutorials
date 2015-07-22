package org.nycteascandiaca.tutorials.library.ui.editors.author;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;
import org.nycteascandiaca.tutorials.library.ui.UIUtils;

class AuthorBooksListCellRenderer implements ListCellRenderer<Book>
{
	private DefaultListCellRenderer cellRenderer;

	AuthorBooksListCellRenderer()
	{
		cellRenderer = new DefaultListCellRenderer();
	}
	
	@Override
	public Component getListCellRendererComponent
	(
			JList<? extends Book> list,
			Book value,
			int index,
			boolean isSelected,
			boolean cellHasFocus
	)
	{
		JLabel result = (JLabel)cellRenderer.getListCellRendererComponent
		(
				list,
				value,
				index,
				isSelected,
				cellHasFocus
		);
		
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		String title = UIUtils.toString(value, EModelProperty.BOOK__TITLE);
		String publicationDate = UIUtils.toString(value, EModelProperty.BOOK__PUBLICATION_DATE);
		String pages = UIUtils.toString(value, EModelProperty.BOOK__PAGES);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");
		sb.append("<b>Title: </b>");
		sb.append(title);
		sb.append("<br>");
		sb.append("<b>Date: </b>");
		sb.append(publicationDate);
		sb.append("<br>");
		sb.append("<b>Pages: </b>");
		sb.append(pages);
		sb.append("</html>");
		
		result.setText(sb.toString());
		result.setIcon(resourceManager.getIcon(EIcon.BOOK_32x32));
		
		return result;
	}
}
