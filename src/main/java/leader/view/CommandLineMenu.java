package leader.view;

import leader.Model;

import java.io.Scanner;

public class CommandLineMenu
{
	private GameMenu _menu;
	private boolean _exitWhenDone;
	private Model _model;
	private View _view;
	
	public CommandLineMenu( GameMenu menu, Model model, View view )
	{
		_menu = menu;
		_exitWhenDone = true;
		_model = model;
		_view = view;
	}
	
	public void display()
	{
		Scanner scanner = _view.getScanner();
		boolean done = false;
		while( !done )
		{
			if( _menu.getTitle() != null && !_menu.getTitle().equals( "" ) )
				System.out.println( _menu.getTitle() );
			for( int i = 0; i < _menu.getItems().size(); ++i )
			{
				GameMenuItem item = _menu.getItems().get( i );
				System.out.print( (i + 1) + ") " );
				System.out.println( item.getDisplay() );
			}
			System.out.print( _menu.getPrompt() );
			String input = _scanner.nextLine();
			int inp = -1;
			try
			{
				inp = Integer.decode( input );
			}
			catch( Exception e )
			{
				System.err.println( "Invalid input" );
				continue;
			}
			inp -= 1;
			if( inp < 0 || inp >= _menu.getItems().size() )
			{
				System.err.println( "Invalid input" );
				continue;
			}
			GameMenuItem selected = _menu.getItems( inp );
			selected.getCallback().performAction( selected, _model, _view );
			if( _exitWhenDone )
			{
				done = true;
				continue;
			}
		}
	}
	
	public GameMenu getMenu(){ return _menu; }
	public void setExitWhenDone( boolean v ){ _exitWhenDone = v; }
}