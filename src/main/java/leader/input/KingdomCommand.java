package leader.input;

public class KingdomSummaryCommand extends InputCommand
{
	public static InputCommand parseInput( String inp )
	{
		String[] parts = inp.split( " " );
		String part = parts[ 0 ].toLowerCase();
		if( part.equals( "summary" ) || part.equals( "kingdom" ) )
			return new KingdomSummaryCommand();
		return null;
	}
	
	public static String getHelpCommandList(){ return "summary, kingdom"; }
	public static String getHelpDescription(){ return "Display Kingdom summary"; }
	
    public KingdomSummaryCommand()
	{
        super();
	}
        
        
    public boolean perform( Game game )
	{
        System.out.println( "Kingdom Summary" );
        System.out.println( "Name: " + game.getKingdom().getName() );
        System.out.println( "Size: " + game.getKingdom().getSqAcres() + " sq. acres" );
		System.out.println( "Population: " + game.getKingdom().getPopulationSize() )
        System.out.println( "Towns:" );
        for( Town town: game.getKingdom().getTowns() )
		{
            System.out.println( "   " + town.getName() + " [Pop: " + town.getPopulation().size() + "]" );
		}
    }
}
