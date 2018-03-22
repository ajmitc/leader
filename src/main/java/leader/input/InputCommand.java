package leader.input;

import java.util.List;
import java.util.ArrayList;

// This class represents parsed user input
public abstract class InputCommand
{	
	private static List<Class> _allCommands = new ArrayList<>();
	static {
		_allCommands.add( DisplayHelpCommand.class );
        _allCommands.add( KingdomSummaryCommand.class );
	}
	
		
	public static InputCommand parse( String inp )
	{
		for( Class cmd: _allCommands )
		{
			Method parseInput = cmd.getMethod( "parseInput", String.class );
			InputCommand inst = (InputCommand) parseInput.invoke( null, inp );
			if( inst != null )
			{
				return inst;
			}
		}
		return null;
	}
	 
	
	// 	Display help information
	public static void displayHelp()
	{
		Map<String, String> help = new HashMap<>();
		int maxLength = 0;
		for( Class cmd: _allCommands )
		{
			Method getHelpCommandList = cmd.getMethod( "getHelpCommandList" );
			String cmdList = (String) getHelpCommandList.invoke( null );
			
			Method getHelpDescription = cmd.getMethod( "getHelpDescription" );
			String desc = (String) getHelpDescription.invoke( null );
			
			help.put( cmdList, desc );
			
			if( cmdList.length() > maxLength )
				maxLength = cmdList.length();
		}
		
		System.out.println( "Leader Help" );
		for( String cmdList: help.keySet() )
		{
			String sep = "";
			for( int i = 0; i < maxLength - cmdList.length() + 4; ++i )
				sep += " ";
			System.out.print( cmdList );
			System.out.print( sep );
			System.out.println( help.get( cmdList ) );
		}
	}
	
	
	
	public InputCommand()
	{
		
	}	
	
	// Return True if the command was handled properly, False otherwise.
    public abstract boolean perform( Game game );
	
	// Subclasses must also implement these static methods
	// public static InputCommand parseInput( String inp );
	// public static String getHelpCommandList();
	// public static String getHelpDescription();
}
