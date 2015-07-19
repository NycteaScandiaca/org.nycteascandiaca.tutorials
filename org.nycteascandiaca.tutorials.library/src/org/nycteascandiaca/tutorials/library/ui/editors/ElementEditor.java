package org.nycteascandiaca.tutorials.library.ui.editors;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.model.ModelElement;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.IView;
import org.nycteascandiaca.tutorials.library.ui.editors.author.AuthorEditor;
import org.nycteascandiaca.tutorials.library.ui.editors.author.AuthorEditorController;
import org.nycteascandiaca.tutorials.library.ui.editors.book.BookEditor;
import org.nycteascandiaca.tutorials.library.ui.editors.book.BookEditorController;

@SuppressWarnings("serial")
public class ElementEditor extends JPanel implements IView<ModelElement>
{
	private ModelElement input;
	
	private IEditorController<?, ?> controller;
		
	public ElementEditor()
	{
		setLayout(new BorderLayout());
	}
	
	@Override
	public EView getType()
	{
		return EView.MODEL_ELEMENT_EDITOR;
	}

	@Override
	public ModelElement getInput()
	{
		return input;
	}

	@Override
	public void setInput(ModelElement input)
	{
		if (controller != null)
		{
			remove(controller.getView());
			controller.dispose();
			controller = null;
		}
		
		this.input = input;
		
		if (input instanceof Book)
		{
			controller = new BookEditorController((Book)input, new BookEditor());
			controller.initialize();
			add(controller.getView(), BorderLayout.CENTER);
		}
		else if (input instanceof Author)
		{
			controller = new AuthorEditorController((Author)input, new AuthorEditor());
			controller.initialize();
			add(controller.getView(), BorderLayout.CENTER);
		}
		
		revalidate();
		repaint();
	}
}
