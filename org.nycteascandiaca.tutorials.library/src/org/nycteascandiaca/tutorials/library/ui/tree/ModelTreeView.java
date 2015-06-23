package org.nycteascandiaca.tutorials.library.ui.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.ILibrary;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.ISelectionChangedListener;
import org.nycteascandiaca.tutorials.library.ui.ISelectionProvider;
import org.nycteascandiaca.tutorials.library.ui.IView;
import org.nycteascandiaca.tutorials.library.ui.Selection;
import org.nycteascandiaca.tutorials.library.ui.SelectionChangeEvent;

@SuppressWarnings("serial")
public class ModelTreeView extends JPanel implements IView<ILibrary>, ISelectionProvider, TreeSelectionListener
{
	private JLabel headerLabel;
	
	private JTree tree;
	
	private ModelTreeModel treeModel;
	
	private ILibrary input;
	
	private Selection selection;
	
	private final List<ISelectionChangedListener> listeners;
	
	public ModelTreeView()
	{
		selection = Selection.EMPTY;
		initializeComponents();
		
		setLayout(new BorderLayout());
		add(headerLabel, BorderLayout.NORTH);
		add(new JScrollPane(tree), BorderLayout.CENTER);
		
		listeners = new LinkedList<ISelectionChangedListener>();
	}
	
	private void initializeComponents()
	{
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		headerLabel = new JLabel(" Model Tree");
		headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
		headerLabel.setOpaque(true);
		headerLabel.setBackground(Color.LIGHT_GRAY);
		headerLabel.setIcon(resourceManager.getIcon(EIcon.LIBRARY_48x48));
		
		tree = new JTree();
		tree.setCellRenderer(new ModelTreeCellRenderer());
		tree.addTreeSelectionListener(this);
	}
	
	@Override
	public EView getType()
	{
		return EView.MODEL_TREE;
	}

	@Override
	public ILibrary getInput()
	{
		return input;
	}

	@Override
	public void setInput(ILibrary input)
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
		TreePath[] paths = e.getPaths();
		List<Object> elements = new ArrayList<Object>(paths.length);
		for (TreePath path : paths)
		{
			if (e.isAddedPath(path))
			{
				Object last = path.getLastPathComponent();
				elements.add(last);
			}
		}
		selection = new Selection(elements);
		fireSelectionChanged(selection);
	}
}
