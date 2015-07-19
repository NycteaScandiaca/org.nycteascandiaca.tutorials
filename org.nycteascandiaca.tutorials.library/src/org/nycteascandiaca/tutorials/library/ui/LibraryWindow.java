package org.nycteascandiaca.tutorials.library.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.actions.EAction;
import org.nycteascandiaca.tutorials.library.actions.IAction;
import org.nycteascandiaca.tutorials.library.ui.editors.ElementEditor;
import org.nycteascandiaca.tutorials.library.ui.tree.ModelTreeView;

@SuppressWarnings("serial")
public class LibraryWindow extends JFrame
{
	private final ModelTreeView modelTreeView;
	
	private final ElementEditor elementEditor;
	
	private final LibraryMenuBar menuBar;
	
	private final LibraryToolBar toolBar;
		
	public LibraryWindow()
	{
		menuBar = new LibraryMenuBar();
		toolBar = new LibraryToolBar();
		
		modelTreeView = new ModelTreeView();
		elementEditor = new ElementEditor();
		
		setJMenuBar(menuBar);
		
		setLayout(new BorderLayout());
		add(toolBar, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(modelTreeView);
		splitPane.setRightComponent(elementEditor);
		add(splitPane, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				IAction action = Application.INSTANCE.getActionManager().getAction(EAction.EXIT);
				action.actionPerformed(null);
			}
		});
		
		setSize(800, 600);
		splitPane.setDividerLocation(250);
	}
		
	public ModelTreeView getModelTreeView()
	{
		return modelTreeView;
	}
	
	public ElementEditor getElementEditor()
	{
		return elementEditor;
	}
}
