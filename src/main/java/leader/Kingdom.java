package leader;

public class Kingdom
{	
	private String _name;
	private List<Town> _towns;
	
	public Kingdom()
	{
    	_name = "Dalriada";
        _towns = new ArrayList<>();
	}
	
        
    public void update( Game game )
	{
        for( Town town: towns )
		{
            town.update( game );
		}
	}
	
	public int getPopulationSize()
	{
		int total = 0;
		for( Town town: towns )
		{
			total += town.getPopulation().size();
		}
		return total;
	}
	
	public String getName(){ return _name; }
	public void setName( String n ){ _name = n; }
	
	/**
	 * Return the square acres of the Kingdom.  This is calculated by finding the
	 * farthest Town's sq acre, doubling the coordinates, and multiplying the result.
	 * It is assumed that the kingdom's land is a rectangle with the capital at the center.
	 */
	public int getSqAcres()
	{ 
		int farthestX = 0, farthestY = 0;
		double farthestDist = 0.0;
		for( Town town: _towns )
		{
			int ts = Maht.sqrt( town.getSqAcres() ) / 2;
			int tx = Math.abs( town.getX() ) + ts;
			int ty = Math.abs( town.getY() ) + ts;
			double dist = Util.getDistance( 0, 0, tx, ty );
			if( dist > farthestDist )
			{
				farthestDist = dist;
				farthestX = tx;
				farthestY = ty;
			}
		}
		return (tx * 2) * (ty * 2); 
	}
	
	public List<Town> getTowns(){ return _towns; }
}