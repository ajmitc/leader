package leader.view;

import leader.Model;
import leader.input.Input;

public class CommandLineView extends View
{
	private Input _input;
	
	public CommandLineView( Model model )
	{
		super( model );
		_input = new Input();
		
		for( GameMenuItem item: _menuMenu.getItems() )
		{
			if( item.getDisplay().equals( "New Game" ) )
			{
				item.setCallback( new GameMenuItemCallback(){
					public void performAction( GameMenuItem item, Model model, View view )
					{
						model.newGame();
						play();
					}
				})
			}
			else if( item.getDisplay().equals( "Exit" ) )
			{
				item.setCallback( new GameMenuItemCallback(){
					public void performAction( GameMenuItem item, Model model, View view )
					{
						System.exit();
					}
				})
			}
		}
	}
	
	public void displayMainMenu()
	{
		CommandLineMenu mainmenu = new CommandLineMenu( _mainMenu, _model, this );
		mainmenu.setExitWhenDone( false );
		mainmenu.display();
	}
	
	public void play()
	{
	
	}
	
	public Input getInput(){ return _input; }
}