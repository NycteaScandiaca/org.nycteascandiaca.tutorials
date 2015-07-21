package org.nycteascandiaca.tutorials.library.ui.editors.library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;

@SuppressWarnings("serial")
public class LibraryEditor extends JPanel
{
	private JLabel headerLable;
	
	private JLabel idLabel;
	
	private JTextField idTextField;
	
	private JLabel nameLabel;
	
	private JTextField nameTextField;
	
	private JLabel descriptionLabel;
	
	private JTextArea descriptionTextArea;
	
	public LibraryEditor()
	{
		initializeComponents();
		
		setLayout(new BorderLayout());
		add(headerLable, BorderLayout.NORTH);
		add(createCenterPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createCenterPanel()
	{
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
		
		JPanel centerPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(centerPanel);
		centerPanel.setLayout(groupLayout);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
		SequentialGroup hGroup = groupLayout.createSequentialGroup();
		hGroup.addGroup
		(
				groupLayout.createParallelGroup()
				.addComponent(idLabel)
				.addComponent(nameLabel)
				.addComponent(descriptionLabel)
		);
		hGroup.addGroup
		(
				groupLayout.createParallelGroup()
				.addComponent(idTextField)
				.addComponent(nameTextField)
				.addComponent(descriptionScrollPane)
		);
		groupLayout.setHorizontalGroup(hGroup);
		
		SequentialGroup vGroup = groupLayout.createSequentialGroup();
		vGroup.addGroup
		(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(idLabel)
				.addComponent(idTextField)
		);
		vGroup.addGroup(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(nameLabel)
				.addComponent(nameTextField)
		);
		vGroup.addGroup
		(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(descriptionLabel)
				.addComponent(descriptionScrollPane)
		);
		groupLayout.setVerticalGroup(vGroup);
		
		return centerPanel;
	}

	private void initializeComponents()
	{
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		headerLable = new JLabel(" Library Editor", JLabel.LEFT);
		headerLable.setFont(new Font("Serif", Font.BOLD, 24));
		headerLable.setOpaque(true);
		headerLable.setBackground(Color.LIGHT_GRAY);
		headerLable.setIcon(resourceManager.getIcon(EIcon.LIBRARY_48x48));
		
		idLabel = new JLabel("Id:");
		idTextField = new JTextField();
		idTextField.setEnabled(false);
		
		nameLabel = new JLabel("Name:");
		nameTextField = new JTextField();
		
		descriptionLabel = new JLabel("Description:");
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
	}
	
	public JTextField getIdTextField()
	{
		return idTextField;
	}
	
	public JTextField getNameTextField()
	{
		return nameTextField;
	}
	
	public JTextArea getDescriptionTextArea()
	{
		return descriptionTextArea;
	}
}
