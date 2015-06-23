package org.nycteascandiaca.tutorials.library.resources;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ResourceManager
{
	public ResourceManager()
	{
		
	}
	
	public Icon getIcon(EIcon icon)
	{
		URL resource = getClass().getResource(icon.getResource());
		if (resource == null)
		{
			return null;
		}
		return new ImageIcon(resource);
	}
}
