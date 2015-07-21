package org.nycteascandiaca.tutorials.library.model.utils;

import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.nycteascandiaca.tutorials.library.model.Library;

public class ModelXMLWriter
{
	public static void write(Library library, Path path) throws JAXBException
	{
		JAXBContext context = JAXBContext.newInstance(Library.class);
		
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(library, System.out);
		m.marshal(library, path.toFile());
	}
}
