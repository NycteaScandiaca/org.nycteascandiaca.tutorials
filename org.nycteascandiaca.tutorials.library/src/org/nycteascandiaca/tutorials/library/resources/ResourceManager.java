package org.nycteascandiaca.tutorials.library.resources;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ResourceManager
{
	private static final String ICONS_ROOT = "icons/";
	
	public ResourceManager()
	{
		
	}
	
	public Icon getIcon(EIcon icon)
	{
		URL resource = getClass().getResource(ICONS_ROOT + icon.getResource());
		if (resource == null)
		{
			return null;
		}
		return new ImageIcon(resource);
	}
}
