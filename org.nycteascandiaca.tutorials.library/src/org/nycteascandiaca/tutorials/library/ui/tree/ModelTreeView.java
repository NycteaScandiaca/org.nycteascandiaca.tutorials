package org.nycteascandiaca.tutorials.library.ui.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.actions.ActionManager;
import org.nycteascandiaca.tutorials.library.actions.EAction;
import org.nycteascandiaca.tutorials.library.actions.IAction;
import org.nycteascandiaca.tutorials.library.model.Library;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.ISelectionChangedListener;
import org.nycteascandiaca.tutorials.library.ui.ISelectionProvider;
import org.nycteascandiaca.tutorials.library.ui.IView;
import org.nycteascandiaca.tutorials.library.ui.Selection;
import org.nycteascandiaca.tutorials.library.ui.SelectionChangeEvent;

@SuppressWarnings("serial")
public class ModelTreeView extends JPanel implements IView<Library>, ISelectionProvider, TreeSelectionListener, MouseListener
{
	private final JLabel headerLabel;
	
	private final JTree tree;
	
	private ModelTreeModel treeModel;
	
	private Library input;
	
	private Selection selection;
	
	private JPopupMenu popupMenu;
	
	private final List<ISelectionChangedListener> listeners;
	
	public ModelTreeView()
	{
		tree = new JTree();
		headerLabel = new JLabel(" Model Tree");
		setLayout(new BorderLayout());
		add(headerLabel, BorderLayout.NORTH);
		add(new JScrollPane(tree), BorderLayout.CENTER);
		
		selection = Selection.EMPTY;
		listeners = new LinkedList<ISelectionChangedListener>();
		
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
		headerLabel.setOpaque(true);
		headerLabel.setBackground(Color.LIGHT_GRAY);
		headerLabel.setIcon(resourceManager.getIcon(EIcon.LIBRARY_48x48));
		
		tree.setModel(null);
		tree.setCellRenderer(new ModelTreeCellRenderer());
		tree.addTreeSelectionListener(this);
		tree.addMouseListener(this);
		
		popupMenu = new JPopupMenu();
	}
	
	@Override
	public EView getType()
	{
		return EView.MODEL_TREE;
	}

	@Override
	public Library getInput()
	{
		return input;
	}

	@Override
	public void setInput(Library input)
	{
		if (treeModel != null)
		{
			tree.setModel(null);
			treeModel.dispose();
			treeModel = null;
		}
		
		this.input = input;
		
		if (input != null)
		{
			treeModel = new ModelTreeModel(input);
		}
		tree.setModel(treeModel);
	}

	@Override
	public Selection getSelection()
	{
		return selection;
	}

	@Override
	public void setSelection(Selection selection)
	{
		List<Object> elements = selection.getElements();
		TreePath[] paths = new TreePath[elements.size()];
		for (int i = 0; i < elements.size(); i++)
		{
			TreePath path = ModelTreeModel.createPath(input, elements.get(i));
			paths[i] = path;
		}
		tree.setSelectionPaths(paths);
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener)
	{
		listeners.remove(listener);
	}

	private void fireSelectionChanged(Selection selection)
	{
		SelectionChangeEvent event = new SelectionChangeEvent(this, selection);
		listeners.forEach(current -> current.selectionChanged(event));
	}

	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		TreePath[] paths = tree.getSelectionPaths();
		if (paths == null)
		{
			if (!selection.getElements().isEmpty())
			{
				fireSelectionChanged(selection);
			}
			return;
		}
		
		List<Object> elements = new ArrayList<Object>(paths.length);
		for (TreePath path : paths)
		{
			Object last = path.getLastPathComponent();
			elements.add(last);
		}
		
		selection = new Selection(elements);
		fireSelectionChanged(selection);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{		
		if (SwingUtilities.isRightMouseButton(e))
		{
			int row = tree.getRowForLocation(e.getX(), e.getY());
			if (row < 0)
			{
				tree.clearSelection();
				return;
			}
			
			int[] selectionRows = tree.getSelectionRows();
			if (selectionRows == null)
			{
				return;
			}
			
			boolean isSelectedRow = false;
			for (int current : selectionRows)
			{
				if (current == row)
				{
					isSelectedRow = true;
					break;
				}
			}
			
			if (!isSelectedRow)
			{
				tree.setSelectionRow(row);
			}
	        
			showPopupMenu(e.getComponent(), e.getX(), e.getY());
	    }
	}
	
	private void showPopupMenu(Component c, int x, int y)
	{
		ActionManager actionManager = Application.INSTANCE.getActionManager();
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		popupMenu.removeAll();
		
		IAction current = actionManager.getAction(EAction.ADD_AUTHOR);
		if (current.isEnabled())
		{
			JMenuItem menuItem = popupMenu.add(current);
			menuItem.setText("Add Author");
			menuItem.setIcon(resourceManager.getIcon(EIcon.AUTHOR_16x16));
		}
		
		current = actionManager.getAction(EAction.ADD_BOOK);
		if (current.isEnabled())
		{
			JMenuItem menuItem = popupMenu.add(current);
			menuItem.setText("Add Book");
			menuItem.setIcon(resourceManager.getIcon(EIcon.BOOK_16x16));
		}
		
		current = actionManager.getAction(EAction.DELETE);
		if (current.isEnabled())
		{
			JMenuItem menuItem = popupMenu.add(current);
			menuItem.setText("Delete");
			menuItem.setIcon(resourceManager.getIcon(EIcon.DELETE_16x16));
		}
		
		popupMenu.show(c, x, y);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// Do nothing
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// Do nothing
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Do nothing
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// Do nothing
	}
}
