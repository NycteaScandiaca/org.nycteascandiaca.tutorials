package org.nycteascandiaca.tutorials.library.ui.editors.book;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.edit.EModelProperty;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;
import org.nycteascandiaca.tutorials.library.ui.UIUtils;

public class BookAuthorsListCellRenderer implements ListCellRenderer<Author>
{
	private DefaultListCellRenderer cellRenderer;

	BookAuthorsListCellRenderer()
	{
		cellRenderer = new DefaultListCellRenderer();
	}
	
	@Override
	public Component getListCellRendererComponent
	(
			JList<? extends Author> list,
			Author value,
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
		
		String firstName = UIUtils.toString(value, EModelProperty.AUTHOR__FIRST_NAME);
		String lastName = UIUtils.toString(value, EModelProperty.AUTHOR__LAST_NAME);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");
		sb.append("<b>Name: </b>");
		sb.append(firstName);
		sb.append(" ");
		sb.append(lastName);
		sb.append("<br>");
		sb.append("<b>Books: </b>");
		sb.append(value.getBooks().size());
		sb.append("</html>");
		
		result.setText(sb.toString());
		result.setIcon(resourceManager.getIcon(EIcon.AUTHOR_32x32));
		
		return result;
	}
}
