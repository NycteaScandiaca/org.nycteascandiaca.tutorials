package org.nycteascandiaca.tutorials.library.model.utils;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateXMLAdapter extends XmlAdapter<String, LocalDate>
{
	@Override
	public LocalDate unmarshal(String value) throws Exception
	{
		return LocalDate.parse(value);
	}

	@Override
	public String marshal(LocalDate value) throws Exception
	{
		return value.toString();
	}
}
