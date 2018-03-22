package leader.population;

public class PopulationStatistics
{
	private Map<PopulationType, Double> _stats;
	
	public PopulationStatistics()
	{
    	_stats = new HashMap<>();
		_stats.put( PopulationType.MALE,       0.0 );
		_stats.put( PopulationType.FEMALE,     0.0 );
		_stats.put( PopulationType.CHILD,      0.0 );
        _stats.put( PopulationType.ADOLESCENT, 0.0 );
		_stats.put( PopulationType.ADULT,      0.0 );
		_stats.put( PopulationType.ELDERLY,    0.0 );
    }
	
	public Double get( PopulationType t )
	{ 
		if( _stats.containsKey( t ) )
			return _stats.get( t );
		return null;
	}
	
	public boolean set( PopulationType t, Double v )
	{
		if( _stats.containsKey( t ) )
		{
			_stats.put( t, v );
			return true;
		}	
		return false;
	}
	
	public boolean inc( PopulationType t, Double v )
	{
		if( _stats.containsKey( t ) )
		{
			_stats.put( t, _stats.get( t ) + v );
			return true;
		}	
		return false;
	}
	
	public Map<PopulationType, Double> getStats(){ return _stats; }
}