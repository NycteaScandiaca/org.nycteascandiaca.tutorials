package org.nycteascandiaca.tutorials.library;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.model.EBookCategory;
import org.nycteascandiaca.tutorials.library.model.IAuthor;
import org.nycteascandiaca.tutorials.library.model.IBook;
import org.nycteascandiaca.tutorials.library.model.ILibrary;
import org.nycteascandiaca.tutorials.library.model.IModelFactory;
import org.nycteascandiaca.tutorials.library.model.impl.ModelFactory;
import org.nycteascandiaca.tutorials.library.ui.EView;
import org.nycteascandiaca.tutorials.library.ui.IView;

public class ModelManager
{
	private final IModelFactory modelFactory;
	
	private final CommandStack commandStack;
	
	private ILibrary library;
	
	private Path path;
	
	ModelManager()
	{
		modelFactory = new ModelFactory();
		commandStack = new CommandStack(30);
	}
	
	public ILibrary getLibrary()
	{
		return library;
	}
	
	@SuppressWarnings("unchecked")
	public void setLibrary(ILibrary library)
	{
		commandStack.clear();
		path = null;
		
		this.library = library;
		
		IView<ILibrary> view = (IView<ILibrary>)Application.INSTANCE.getView(EView.MODEL_TREE);
		view.setInput(library);
	}
	
	public Path getPath()
	{
		return path;
	}
	
	public void setPath(Path path)
	{
		this.path = path;
	}
	
	public IModelFactory getModelFactory()
	{
		return modelFactory;
	}
	
	public CommandStack getCommandStack()
	{
		return commandStack;
	}
	
	public void openDemoModel()
	{
		ILibrary demoLibrary = createDemoModel();
		setLibrary(demoLibrary);
	}
	
	private static ILibrary createDemoModel()
	{		
		IModelFactory modelFactory = Application.INSTANCE.getModelManager().getModelFactory();
		
		IAuthor author = modelFactory.createAuthor();
		author.setId(UUID.randomUUID().toString());
		author.setFirstName("Frank");
		author.setLastName("Luna");
		
		IBook book = modelFactory.createBook();
		book.setId(UUID.randomUUID().toString());
		book.setTitle("Introduction to 3D Game Programming with DirectX 12");
		book.setCategory(EBookCategory.COMPUTER_SCIENCE);
		book.setPublicationDate(LocalDate.of(2015, 12, 15));
		book.setPages(900);
		book.setDescription("This updated international bestseller provides an introduction to programming interactive computer graphics with an emphasis on game development using DirectX 12.\nThe book is divided into three main parts: basic mathematical tools, fundamental tasks in Direct3D, and techniques and special effects.\nIt shows how to use new DirectX12 features such as command lists, bundles, pipeline state objects, descriptor heaps and tables, and explicit resource management to reduce CPU overhead and increase scalability across multiple CPU cores.\nThe book covers modern special effects and techniques such as hardware tessellation, computer shaders, ambient occlusion, reflections, normal and displacement mapping, shadow rendering, particle systems, and character animation.\nIt includes a companion DVD with code and figures.");
		
		ILibrary library = modelFactory.createLibrary();
		library.setId(UUID.randomUUID().toString());
		library.setName("Demo Library");
		library.getBooks().add(book);
		library.getAuthors().add(author);
		
		author.getBooks().add(book);
		book.getAuthors().add(author);
		
		return library;
	}
}
