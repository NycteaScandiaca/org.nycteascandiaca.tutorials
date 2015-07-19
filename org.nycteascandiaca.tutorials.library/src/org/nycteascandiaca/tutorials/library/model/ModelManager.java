package org.nycteascandiaca.tutorials.library.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.nycteascandiaca.tutorials.library.Application;
import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.IView;

public class ModelManager
{
	private final ModelFactory modelFactory;
	
	private final CommandStack commandStack;
	
	private final List<IModelManagerListener> listeners;
	
	private Library model;
	
	private Path path;
	
	public ModelManager()
	{
		modelFactory = new ModelFactory();
		commandStack = new CommandStack(30);
		listeners = new LinkedList<IModelManagerListener>();
	}
	
	public Library getModel()
	{
		return model;
	}
	
	public Path getPath()
	{
		return path;
	}
	
	public ModelFactory getModelFactory()
	{
		return modelFactory;
	}
	
	public CommandStack getCommandStack()
	{
		return commandStack;
	}
	
	public void addModelManagerListener(IModelManagerListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeModelManagerListener(IModelManagerListener listener)
	{
		listeners.remove(listener);
	}
	
	public Library newModel()
	{
		commandStack.clear();
		
		//model = modelFactory.createLibrary();
		//model.setName("New Library");
		
		model = createDemoModel();	
		path = null;
		
		fireModelOpened(model, path);
		
		return model;
	}
	
	public Library openModel(Path path)
	{
		commandStack.clear();
		
		model = null;
		path = null;
		
		// TODO ...
		
		fireModelOpened(model, path);
		
		return model;
	}
	
	public void saveModel()
	{
		if (model == null || path == null)
		{
			throw new IllegalStateException();
		}
		
		// TODO ...
		
		fireModelSaved(model, path);
	}
	
	public void saveModelAs(Path path)
	{
		if (model == null)
		{
			throw new IllegalStateException();
		}
		
		// TODO ...
		
		fireModelSaved(model, path);
	}
	
	public void closeModel()
	{
		commandStack.clear();
		
		Library model = this.model;
		this.model = null;
		path = null;
		
		fireModelClosed(model);
	}
	
	public Library openDemoModel()
	{
		commandStack.clear();
		
		model = createDemoModel();
		path = null;
		
		fireModelOpened(model, path);
		
		return model;
	}
	
	public void setModel(Library model)
	{
		commandStack.clear();
		path = null;
		
		this.model = model;
	}
		
	private void fireModelClosed(Library library)
	{
		listeners.forEach(current -> current.modelClosed(model));
	}
	
	private void fireModelOpened(Library library, Path path)
	{
		listeners.forEach(current -> current.modelOpened(model, path));
	}
	
	private void fireModelSaved(Library model, Path path)
	{
		listeners.forEach(current -> current.modelSaved(model, path));
	}
	
	private static Library createDemoModel()
	{		
		ModelFactory modelFactory = Application.INSTANCE.getModelManager().getModelFactory();
		
		Author author = modelFactory.createAuthor();
		author.setId(UUID.randomUUID().toString());
		author.setFirstName("Frank");
		author.setLastName("Luna");
		
		Book book = modelFactory.createBook();
		book.setId(UUID.randomUUID().toString());
		book.setTitle("Introduction to 3D Game Programming with DirectX 12");
		book.setCategory(EBookCategory.COMPUTER_SCIENCE);
		book.setPublicationDate(LocalDate.of(2015, 12, 15));
		book.setPages(900);
		book.setDescription("This updated international bestseller provides an introduction to programming interactive computer graphics with an emphasis on game development using DirectX 12.\nThe book is divided into three main parts: basic mathematical tools, fundamental tasks in Direct3D, and techniques and special effects.\nIt shows how to use new DirectX12 features such as command lists, bundles, pipeline state objects, descriptor heaps and tables, and explicit resource management to reduce CPU overhead and increase scalability across multiple CPU cores.\nThe book covers modern special effects and techniques such as hardware tessellation, computer shaders, ambient occlusion, reflections, normal and displacement mapping, shadow rendering, particle systems, and character animation.\nIt includes a companion DVD with code and figures.");
		
		Library library = modelFactory.createLibrary();
		library.setId(UUID.randomUUID().toString());
		library.setName("Demo Library");
		library.getBooks().add(book);
		library.getAuthors().add(author);
		
		author.getBooks().add(book);
		book.getAuthors().add(author);
		
		return library;
	}
}
