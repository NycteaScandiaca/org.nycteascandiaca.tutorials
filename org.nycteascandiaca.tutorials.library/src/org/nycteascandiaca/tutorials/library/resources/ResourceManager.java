package org.nycteascandiaca.tutorials.library.resources;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ResourceManager
{
	private static final String ICONS_ROOT = "icons/";
	
	private Map<EIcon, Icon> iconCache;
	
	public ResourceManager()
	{
		iconCache = new HashMap<EIcon, Icon>();
	}
	
	public Icon getIcon(EIcon icon)
	{
		Icon result = iconCache.get(icon);
		if (result == null)
		{
			URL resource = getClass().getResource(ICONS_ROOT + icon.getResource());
			if (resource == null)
			{
				return null;
			}
			result = new ImageIcon(resource);
		}
		return result;
	}
}
