package org.nycteascandiaca.tutorials.library.ui.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.Book;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;

@SuppressWarnings("serial")
public class AuthorEditor extends JPanel
{	
	private JLabel headerLable;
	
	private JLabel idLabel;
	
	private JTextField idTextField;
	
	private JLabel firstNameLabel;
	
	private JTextField firstNameTextField;
	
	private JLabel lastNameLabel;
	
	private JTextField lastNameTextField;
	
	private JLabel descriptionLabel;
	
	private JTextArea descriptionTextArea;
	
	private JLabel booksLabel;
	
	private JList<Book> booksList;
	
	AuthorEditor()
	{
		initializeComponents();
		
		setLayout(new BorderLayout());
		add(headerLable, BorderLayout.NORTH);
		add(createCenterPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createCenterPanel()
	{
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
		JScrollPane booksScrollPane = new JScrollPane(booksList);
		
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
				.addComponent(firstNameLabel)
				.addComponent(lastNameLabel)
				.addComponent(descriptionLabel)
				.addComponent(booksLabel)
		);
		hGroup.addGroup
		(
				groupLayout.createParallelGroup()
				.addComponent(idTextField)
				.addComponent(firstNameTextField)
				.addComponent(lastNameTextField)
				.addComponent(descriptionScrollPane)
				.addComponent(booksScrollPane)
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
				.addComponent(firstNameLabel)
				.addComponent(firstNameTextField)
		);
		vGroup.addGroup
		(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lastNameLabel)
				.addComponent(lastNameTextField)
		);
		vGroup.addGroup
		(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(descriptionLabel)
				.addComponent(descriptionScrollPane)
		);
		vGroup.addGroup
		(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(booksLabel)
				.addComponent(booksScrollPane, Alignment.BASELINE)
		);
		groupLayout.setVerticalGroup(vGroup);
		
		return centerPanel;
	}

	private void initializeComponents()
	{
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		headerLable = new JLabel(" Author Editor", JLabel.LEFT);
		headerLable.setFont(new Font("Serif", Font.BOLD, 24));
		headerLable.setOpaque(true);
		headerLable.setBackground(Color.LIGHT_GRAY);
		headerLable.setIcon(resourceManager.getIcon(EIcon.AUTHOR_48x48));
		
		idLabel = new JLabel("Id:");
		idTextField = new JTextField();
		idTextField.setEnabled(false);
		
		firstNameLabel = new JLabel("First name:");
		firstNameTextField = new JTextField();
		
		lastNameLabel = new JLabel("Last name:");
		lastNameTextField = new JTextField();
		
		descriptionLabel = new JLabel("Description:");
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		booksLabel = new JLabel("Books:");
		booksList = new JList<Book>();
	}
	
	public JTextField getIdTextField()
	{
		return idTextField;
	}
	
	public JTextField getFirstNameTextField()
	{
		return firstNameTextField;
	}
	
	public JTextField getLastNameTextField()
	{
		return lastNameTextField;
	}
	
	public JTextArea getDescriptionTextArea()
	{
		return descriptionTextArea;
	}
	
	public JList<Book> getBooksList()
	{
		return booksList;
	}
}
