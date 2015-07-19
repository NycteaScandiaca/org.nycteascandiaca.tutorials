package org.nycteascandiaca.tutorials.library.ui.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.model.Author;
import org.nycteascandiaca.tutorials.library.model.EBookCategory;
import org.nycteascandiaca.tutorials.library.resources.EIcon;
import org.nycteascandiaca.tutorials.library.resources.ResourceManager;

@SuppressWarnings("serial")
public class BookEditor extends JPanel
{	
	private JLabel headerLabel;
	
	private JLabel idLabel;
	
	private JTextField idTextField;
	
	private JLabel titleLabel;
	
	private JTextField titleTextField;
	
	private JLabel categoryLabel;
	
	private JComboBox<EBookCategory> categoryComboBox;
	
	private JLabel publicationDateLabel;
	
	private JTextField publicationDateTextField;
	
	private JLabel pagesLabel;
	
	private JTextField pagesTextField;
	
	private JLabel descriptionLabel;
	
	private JTextArea descriptionTextArea;
	
	private JLabel authorsLabel;
	
	private JList<Author> authorsList;
	
	BookEditor()
	{
		initializeComponents();
		
		setLayout(new BorderLayout());
		add(headerLabel, BorderLayout.NORTH);
		add(createCenterPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createCenterPanel()
	{
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
		JScrollPane booksScrollPane = new JScrollPane(authorsList);
		
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
				.addComponent(titleLabel)
				.addComponent(categoryLabel)
				.addComponent(publicationDateLabel)
				.addComponent(pagesLabel)
				.addComponent(descriptionLabel)
				.addComponent(authorsLabel)
		);
		hGroup.addGroup
		(
				groupLayout.createParallelGroup()
				.addComponent(idTextField)
				.addComponent(titleTextField)
				.addComponent(categoryComboBox)
				.addComponent(publicationDateTextField)
				.addComponent(pagesTextField)
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
				.addComponent(titleLabel)
				.addComponent(titleTextField)
		);
		vGroup.addGroup
		(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(categoryLabel)
				.addComponent(categoryComboBox)
		);
		vGroup.addGroup
		(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(publicationDateLabel)
				.addComponent(publicationDateTextField)
		);
		vGroup.addGroup
		(
				groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(pagesLabel)
				.addComponent(pagesTextField)
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
				.addComponent(authorsLabel)
				.addComponent(booksScrollPane, Alignment.BASELINE)
		);
		groupLayout.setVerticalGroup(vGroup);
		
		return centerPanel;
	}

	private void initializeComponents()
	{
		ResourceManager resourceManager = Application.INSTANCE.getResourceManager();
		
		headerLabel = new JLabel(" Book Editor", JLabel.LEFT);
		headerLabel.setFont(new Font(Font.SERIF, Font.BOLD, 24));
		headerLabel.setOpaque(true);
		headerLabel.setBackground(Color.LIGHT_GRAY);
		headerLabel.setIcon(resourceManager.getIcon(EIcon.BOOK_48x48));
		
		idLabel = new JLabel("Id:");
		idTextField = new JTextField();
		idTextField.setEnabled(false);
		
		titleLabel = new JLabel("Title:");
		titleTextField = new JTextField();
		
		categoryLabel = new JLabel("Category:");
		categoryComboBox = new JComboBox<EBookCategory>(EBookCategory.values());
		
		publicationDateLabel = new JLabel("Publication Date:");
		publicationDateTextField = new JTextField();
		
		pagesLabel = new JLabel("Pages:");
		pagesTextField = new JTextField();
		
		descriptionLabel = new JLabel("Description:");
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		authorsLabel = new JLabel("Authors:");
		authorsList = new JList<Author>();
	}
	
	public JTextField getIdTextField()
	{
		return idTextField;
	}
	
	public JTextField getTitleTextField()
	{
		return titleTextField;
	}
	
	public JComboBox<EBookCategory> getCategoryComboBox()
	{
		return categoryComboBox;
	}
	
	public JTextField getPublicationDateTextField()
	{
		return publicationDateTextField;
	}
	
	public JTextField getPagesTextField()
	{
		return pagesTextField;
	}
	
	public JTextArea getDescriptionTextArea()
	{
		return descriptionTextArea;
	}
	
	public JList<Author> getAuthorsList()
	{
		return authorsList;
	}
}
