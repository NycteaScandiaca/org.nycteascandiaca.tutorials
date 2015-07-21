package org.nycteascandiaca.tutorials.library.model.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.nycteascandiaca.tutorials.library.model.Library;

public class ModelXMLReader
{
	public static Library read(Path path) throws JAXBException, FileNotFoundException
	{
		JAXBContext context = JAXBContext.newInstance(Library.class);
		Unmarshaller um = context.createUnmarshaller();
		Library library = (Library) um.unmarshal(new FileReader(path.toFile()));
		return library;
	}
}
