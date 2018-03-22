package leader.view;

import leader.Model;

public abstract class View
{
	protected Model _model;
	protected GameMenu _mainMenu;
	
	public View( Model model )
	{
		_model = model;
		_mainMenu = new GameMenu( "Leader" );
		GameMenuItem item = new GameMenuItem( "New Game" );
		_mainMenu.getItems().add( item );
		item = new GameMenuItem( "Exit" );
		_mainMenu.getItems().add( item );
	}
	
	public abstract void displayMainMenu();
	
	public GameMenu getMainMenu(){ return _mainMenu; }
}