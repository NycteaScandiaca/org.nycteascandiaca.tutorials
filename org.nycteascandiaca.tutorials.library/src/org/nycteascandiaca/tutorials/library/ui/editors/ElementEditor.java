package org.nycteascandiaca.tutorials.library.ui.editors;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.nycteascandiaca.tutorials.library.model.IAuthor;
import org.nycteascandiaca.tutorials.library.model.IBook;
import org.nycteascandiaca.tutorials.library.model.IModelElement;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.IController;
import org.nycteascandiaca.tutorials.library.ui.IView;

@SuppressWarnings("serial")
public class ElementEditor extends JPanel implements IView<IModelElement>
{
	private IModelElement input;
	
	private IController controller;
		
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
	public IModelElement getInput()
	{
		return input;
	}

	@Override
	public void setInput(IModelElement input)
	{
		if (controller != null)
		{
			remove(controller.getView());
			controller.dispose();
			controller = null;
		}
		
		this.input = input;
		
		if (input instanceof IBook)
		{
			controller = new BookEditorController((IBook)input, new BookEditor());
			controller.initialize();
			add(controller.getView(), BorderLayout.CENTER);
		}
		else if (input instanceof IAuthor)
		{
			controller = new AuthorEditorController((IAuthor)input, new AuthorEditor());
			controller.initialize();
			add(controller.getView(), BorderLayout.CENTER);
		}
		
		revalidate();
		repaint();
	}
}
