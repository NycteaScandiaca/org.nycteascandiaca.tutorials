package org.nycteascandiaca.tutorials.library.model;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.nycteascandiaca.tutorials.library.commands.CommandStack;
import org.nycteascandiaca.tutorials.library.model.utils.ModelXMLReader;
import org.nycteascandiaca.tutorials.library.model.utils.ModelXMLWriter;

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
		model = modelFactory.createLibrary();
		model.setName("New Library");
		path = null;
		commandStack.clear();
		
		fireModelOpened(model, path);
		
		return model;
	}
	
	public Library openModel(Path path) throws FileNotFoundException, JAXBException
	{
		model = ModelXMLReader.read(path);
		this.path = path;
		commandStack.clear();
		
		fireModelOpened(model, path);
		
		return model;
	}
	
	public void saveModel() throws JAXBException
	{
		if (model == null || path == null)
		{
			throw new IllegalStateException();
		}
		
		ModelXMLWriter.write(model, path);
		
		fireModelSaved(model, path);
	}
	
	public void saveModelAs(Path path) throws JAXBException
	{
		if (path == null)
		{
			throw new IllegalArgumentException("Argument 'path' is null");
		}		
		if (model == null)
		{
			throw new IllegalStateException();
		}
		
		ModelXMLWriter.write(model, path);
		this.path = path;
		
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
}
