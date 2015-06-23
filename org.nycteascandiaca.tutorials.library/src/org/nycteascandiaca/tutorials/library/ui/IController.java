package org.nycteascandiaca.tutorials.library.ui;

import java.awt.Component;

import org.nycteascandiaca.tutorials.library.model.IModelElement;

public interface IController
{
	public Component getView();
	
	public IModelElement getModel();
	
	public void initialize();
	
	public void dispose();
}
