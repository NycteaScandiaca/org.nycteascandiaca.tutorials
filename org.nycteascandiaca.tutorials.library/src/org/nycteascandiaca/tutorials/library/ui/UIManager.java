package org.nycteascandiaca.tutorials.library.ui;

import java.nio.file.Path;
import java.util.List;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.IModelManagerListener;
import org.nycteascandiaca.tutorials.library.model.Library;
import org.nycteascandiaca.tutorials.library.model.ModelElement;
import org.nycteascandiaca.tutorials.library.ui.tree.ModelTreeView;

public class UIManager implements ISelectionChangedListener, IModelManagerListener
{
	private LibraryWindow window;
	
	public UIManager()
	{
		
	}
	
	public LibraryWindow getWindow()
	{
		return window;
	}
	
	public IView<?> getView(EView view)
	{
		switch(view)
		{
			case MODEL_TREE:
			{
				return window.getModelTreeView();
			}
			case MODEL_ELEMENT_EDITOR:
			{
				return window.getElementEditor();
			}
		}
		throw new IllegalArgumentException("Not supported view");
	}
	
	public void initialize()
	{	
		this.window = new LibraryWindow();
		
		ISelectionProvider modelTreeView = (ISelectionProvider)getView(EView.MODEL_TREE);
		modelTreeView.addSelectionChangedListener(this);
		
		Application.INSTANCE.getModelManager().addModelManagerListener(this);
	}
	
	public void dispose()
	{
		ISelectionProvider modelTreeView = (ISelectionProvider)getView(EView.MODEL_TREE);
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
			
			IView<ModelElement> view = (IView<ModelElement>)getView(EView.MODEL_ELEMENT_EDITOR);
			if (elements.size() == 1)
			{
				Object element = elements.iterator().next();
				if (element instanceof ModelElement)
				{
					view.setInput((ModelElement)element);
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
	
	@Override
	@SuppressWarnings("unchecked")
	public void modelOpened(Library library, Path path)
	{
		IView<Library> view = (IView<Library>)getView(EView.MODEL_TREE);
		view.setInput(library);
	}

	@Override
	public void modelSaved(Library library, Path path)
	{
		// Do nothing
	}

	@Override
	@SuppressWarnings("unchecked")
	public void modelClosed(Library library)
	{
		IView<Library> view = (IView<Library>)getView(EView.MODEL_TREE);
		view.setInput(null);
	}
}
