package org.nycteascandiaca.tutorials.library;

import javax.swing.UnsupportedLookAndFeelException;

import org.nycteascandiaca.tutorials.library.actions.ActionManager;
import org.nycteascandiaca.tutorials.library.model.ModelManager;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;
import org.nycteascandiaca.tutorials.library.ui.UIManager;

public final class Application
{
	public static final Application INSTANCE = new Application();
	
	private final ModelManager modelManager;
	
	private final ActionManager actionManager;
	
	private final UIManager uiManager;
	
	private final ResourceManager resourceManager;
	
	private Application()
	{
		modelManager = new ModelManager();
		actionManager = new ActionManager();
		resourceManager = new ResourceManager();
		uiManager = new UIManager();
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
	
	public UIManager getUIManager()
	{
		return uiManager;
	}
	
	public void run()
	{		
		actionManager.initialize();
		uiManager.initialize();	
		
		uiManager.getWindow().setVisible(true);
	}
	
	public void exit()
	{	
		uiManager.getWindow().setVisible(false);
		uiManager.getWindow().dispose();
		System.exit(0);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{		
		javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		
		INSTANCE.run();	
	}
}
