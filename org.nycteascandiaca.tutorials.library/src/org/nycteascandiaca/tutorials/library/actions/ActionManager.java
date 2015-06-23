package org.nycteascandiaca.tutorials.library.actions;

import java.util.HashMap;
import java.util.Map;

public class ActionManager
{
	private final Map<EAction, IAction> actions;
	
	public ActionManager()
	{
		actions = new HashMap<EAction, IAction>();
		
		actions.put(EAction.NEW_MODEL, new NewModelAction());
		actions.put(EAction.OPEN_MODEL, new OpenModelAction());
		actions.put(EAction.SAVE_MODEL, new SaveModelAction());
		actions.put(EAction.SAVE_AS_MODEL, new SaveAsModelAction());
		actions.put(EAction.CLOSE_MODEL, new CloseModelAction());
		actions.put(EAction.EXIT, new ExitAction());
		
		actions.put(EAction.UNDO, new UndoAction());
		actions.put(EAction.REDO, new RedoAction());
	}
	
	public void initialize()
	{
		actions.values().forEach(current -> current.initialize());
	}
	
	public void dispose()
	{
		actions.values().forEach(current -> current.dispose());
	}
	
	public IAction getAction(EAction action)
	{
		IAction result = actions.get(action);
		if (result == null)
		{
			throw new IllegalArgumentException("Not supported action");
		}
		return result;
	}
}
