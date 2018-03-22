package leader.input;

public class DisplayHelpCommand extends InputCommand
{
	public static InputCommand parseInput( String inp )
	{
		String[] parts = inp.split( " " );
		String part = parts[ 0 ].toLowerCase();
		if( part.equals( "help" ) || part.equals( "?" ) )
			return new DisplayHelpCommand();
		return null;
	}
	
	public static String getHelpCommandList(){ return "help, ?"; }
	public static String getHelpDescription(){ return "Display this help information"; }
	
	public DisplayHelpCommand()
	{
		super();
	}
	
	public boolean perform( Game game )
	{
		super.displayHelp();
		return true;
	}
}
