package org.nycteascandiaca.tutorials.library.actions;

import javax.swing.Action;

public interface IAction extends Action
{
	public void initialize();
	
	public void dispose();
}
