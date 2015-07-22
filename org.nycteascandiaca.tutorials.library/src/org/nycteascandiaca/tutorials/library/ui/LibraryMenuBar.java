package org.nycteascandiaca.tutorials.library.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.actions.ActionManager;
import org.nycteascandiaca.tutorials.library.actions.EAction;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;

@SuppressWarnings("serial")
public class LibraryMenuBar extends JMenuBar
{
	public LibraryMenuBar()
	{
		add(createFileMenu());
		add(createEditMenu());
	}
		
	private JMenu createFileMenu()
	{
		ActionManager actionManager = Application.INSTANCE.getActionManager();
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem item = fileMenu.add(actionManager.getAction(EAction.NEW_MODEL));
		item.setText("New");
		item.setIcon(resourceManager.getIcon(EIcon.NEW_MODEL_16x16));
		
		item = fileMenu.add(actionManager.getAction(EAction.OPEN_MODEL));
		item.setText("Open...");
		item.setIcon(resourceManager.getIcon(EIcon.OPEN_MODEL_16x16));
		
		fileMenu.addSeparator();
		
		item = fileMenu.add(actionManager.getAction(EAction.CLOSE_MODEL));
		item.setText("Close");
		item.setIcon(resourceManager.getIcon(EIcon.CLOSE_MODEL_16x16));
		
		fileMenu.addSeparator();
		
		item = fileMenu.add(actionManager.getAction(EAction.SAVE_MODEL));
		item.setText("Save");
		item.setIcon(resourceManager.getIcon(EIcon.SAVE_MODEL_16x16));
		
		item = fileMenu.add(actionManager.getAction(EAction.SAVE_AS_MODEL));
		item.setText("Save As...");
		item.setIcon(resourceManager.getIcon(EIcon.SAVE_AS_MODEL_16x16));
		
		fileMenu.addSeparator();
		
		item = fileMenu.add(actionManager.getAction(EAction.EXIT));
		item.setText("Exit");
		item.setIcon(resourceManager.getIcon(EIcon.EXIT_16x16));
		
		return fileMenu;
	}
	
	private JMenu createEditMenu()
	{	
		ActionManager actionManager = Application.INSTANCE.getActionManager();
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem item = editMenu.add(actionManager.getAction(EAction.UNDO));
		item.setText("Undo");
		item.setIcon(resourceManager.getIcon(EIcon.UNDO_16x16));
		
		item = editMenu.add(actionManager.getAction(EAction.REDO));
		item.setText("Redo");
		item.setIcon(resourceManager.getIcon(EIcon.REDO_16x16));
		
		editMenu.addSeparator();
		
		item = editMenu.add(actionManager.getAction(EAction.ADD_AUTHOR));
		item.setText("Add Author");
		item.setIcon(resourceManager.getIcon(EIcon.AUTHOR_16x16));
		
		item = editMenu.add(actionManager.getAction(EAction.ADD_BOOK));
		item.setText("Add Book");
		item.setIcon(resourceManager.getIcon(EIcon.BOOK_16x16));
		
		editMenu.addSeparator();
		
		item = editMenu.add(actionManager.getAction(EAction.DELETE));
		item.setText("Delete");
		item.setIcon(resourceManager.getIcon(EIcon.DELETE_16x16));
		
		return editMenu;
	}
}
