package org.nycteascandiaca.tutorials.library;

import java.util.List;

import org.nycteascandiaca.tutorials.library.model.IModelElement;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.Selection;
import org.nycteascandiaca.tutorials.library.ui.ISelectionChangedListener;
import org.nycteascandiaca.tutorials.library.ui.ISelectionProvider;
import org.nycteascandiaca.tutorials.library.ui.IView;
import org.nycteascandiaca.tutorials.library.ui.SelectionChangeEvent;
import org.nycteascandiaca.tutorials.library.ui.tree.ModelTreeView;

public class SelectionManager implements ISelectionChangedListener
{
	SelectionManager()
	{
		
	}
	
	void initialize()
	{
		ISelectionProvider modelTreeView = (ISelectionProvider)Application.INSTANCE.getView(EView.MODEL_TREE);
		modelTreeView.addSelectionChangedListener(this);
	}
	
	void dispose()
	{
		ISelectionProvider modelTreeView = (ISelectionProvider)Application.INSTANCE.getView(EView.MODEL_TREE);
		modelTreeView.removeSelectionChangedListener(this);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void selectionChanged(SelectionChangeEvent event)
	{
		if (event.getSource() instanceof ModelTreeView)
		{
			Selection selection = event.getSelection();
			List<Object> elements = selection.getElements();
			
			IView<IModelElement> view = (IView<IModelElement>)Application.INSTANCE.getView(EView.MODEL_ELEMENT_EDITOR);
			if (elements.size() == 1)
			{
				Object element = elements.iterator().next();
				if (element instanceof IModelElement)
				{
					view.setInput((IModelElement)element);
				}
				else
				{
					view.setInput(null);
				}
			}
			else
			{
				view.setInput(null);
			}
		}
	}
}
