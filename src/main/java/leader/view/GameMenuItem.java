package leader.view;

public class GameMenuItem
{
	private String _display;
	private GameMenuItemCallback _callback;
	
	public GameMenuItem( String display, GameMenuItemCallback callback )
	{
		_display = display;
		_callback = callback;
	}
	
	public String getDisplay(){ return _display; }
	public GameMenuItemCallback getCallback(){ return _callback; }
}