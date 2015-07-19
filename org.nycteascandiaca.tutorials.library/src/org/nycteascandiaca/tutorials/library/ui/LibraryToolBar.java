package org.nycteascandiaca.tutorials.library.ui;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.actions.ActionManager;
import org.nycteascandiaca.tutorials.library.actions.EAction;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;

@SuppressWarnings("serial")
public class LibraryToolBar extends JToolBar
{
	public LibraryToolBar()
	{
		ActionManager actionManager = Application.INSTANCE.getActionManager();
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		JButton button = add(actionManager.getAction(EAction.NEW_MODEL));
		button.setIcon(resourceManager.getIcon(EIcon.NEW_MODEL_32x32));
		
		button = add(actionManager.getAction(EAction.OPEN_MODEL));
		button.setIcon(resourceManager.getIcon(EIcon.OPEN_MODEL_32x32));
		
		addSeparator();
		
		button = add(actionManager.getAction(EAction.SAVE_MODEL));
		button.setIcon(resourceManager.getIcon(EIcon.SAVE_MODEL_32x32));
		
		addSeparator();
		
		button = add(actionManager.getAction(EAction.UNDO));
		button.setIcon(resourceManager.getIcon(EIcon.UNDO_32x32));
		
		button = add(actionManager.getAction(EAction.REDO));
		button.setIcon(resourceManager.getIcon(EIcon.REDO_32x32));
	}
}
