package leader.input;

import java.io.Scanner;

public class Input
{	
	private Scanner _scanner;
	
	public Input()
	{
		_scanner = new Scanner( System.in );
	}
	
	public InputCommand getInput( String prompt )
	{
		System.out.print( prompt );
		String inp = _scanner.nextLine().trim();
		// attempt to parse it and construct command, if unable, return raw input
		InputCommand cmd = InputCommand.parse( inp );
		return cmd;
	}
	
	public void waitForInput()
	{
		System.out.print( "(Press Enter to Continue)" );
		_scanner.nextLine();
	}
}
