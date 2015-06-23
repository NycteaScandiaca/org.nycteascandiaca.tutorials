package org.nycteascandiaca.tutorials.library;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.nycteascandiaca.tutorials.library.actions.ActionManager;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.IView;
import org.nycteascandiaca.tutorials.library.ui.LibraryWindow;

public final class Application
{
	public static final Application INSTANCE = new Application();
	
	private final ModelManager modelManager;
	
	private final ActionManager actionManager;
	
	private final SelectionManager selectionManager;
	
	private final ResourceManager resourceManager;
	
	private LibraryWindow window;
	
	private Application()
	{
		modelManager = new ModelManager();
		actionManager = new ActionManager();
		selectionManager = new SelectionManager();
		resourceManager = new ResourceManager();
	}
	
	public ModelManager getModelManager()
	{
		return modelManager;
	}
	
	public ActionManager getActionManager()
	{
		return actionManager;
	}
	
	public ResourceManager getResourceManager()
	{
		return resourceManager;
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
	
	public void run()
	{
		window = new LibraryWindow();
		window.setSize(800, 600);
		
		actionManager.initialize();
		selectionManager.initialize();	
		
		INSTANCE.window.setVisible(true);
	}
	
	public void exit()
	{
		window.setVisible(false);
		window.dispose();
		System.exit(0);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		INSTANCE.run();
		
	}
}
