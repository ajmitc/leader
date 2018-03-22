package leader;

import leader.kingdom.Kingdom;
import leader.input.Input;

public class Game
{
    public static final int TICKS_PER_YEAR = 12;
    
    public static final int AGE_ADVANCEMENT_DURATION = TICKS_PER_YEAR * 10;
    
    public static final double ELDERLY_DEATH_PERCENTAGE    = 0.9;  // Percent chance an elderly person dies  (higher percentage means shorter lifespan)
    public static final double ADULT_DEATH_PERCENTAGE      = 0.1;  // Percent chance an adult dies
    public static final double ADOLESCENT_DEATH_PERCENTAGE = 0.05; // Percent chance an adolescent dies
    public static final double CHILD_DEATH_PERCENTAGE      = 0.1;
    
    public static final double ADULT_PROCREATION_PERCENTAGE = 0.8; // Percentage of adult population trying to procreate
    public static final double FERTILITY_PERCENTAGE         = 0.1; // Likelihood that an adult couple will conceive a child
    public static final double IN_UTERO_DEATH_PERCENTAGE    = 0.1; // Likelihood that a pregnancy will abort or end in still-birth
    public static final double INFANT_MORTALITY_PERCENTAGE  = 0.1; // Likelihood a newborn will die (evaluated immediately after birth)
    
	private boolean _quit = false;
    private Kingdom _kingdom;
	private int _timeTillAgeAdvancement;
	private Map<PopulationType, Double> _deathRate;
    private double _adultProcreationRate;
    private double _fertilityRate;
    private double _inUteroDeathRate;
    private double _infantMortalityRate;
	
	public Game()
	{
        _quit = false;
		_input = new Input();
        _kingdom = Kingdom();
		_timeTillAgeAdvancement = AGE_ADVANCEMENT_DURATION;
	    _deathRate = new HashMap<PopulationType, Double>();
		_deathRate.put( PopulationType.ELDERLY,    ELDERLY_DEATH_PERCENTAGE );
        _deathRate.put( PopulationType.ADULT,      ADULT_DEATH_PERCENTAGE );
        _deathRate.put( PopulationType.ADOLESCENT, ADOLESCENT_DEATH_PERCENTAGE );
        _deathRate.put( PopulationType.CHILD,      CHILD_DEATH_PERCENTAGE );
        _adultProcreationRate = ADULT_PROCREATION_PERCENTAGE;
        _fertilityRate = FERTILITY_PERCENTAGE;
        _inUteroDeathRate = IN_UTERO_DEATH_PERCENTAGE;
        _infantMortalityRate = INFANT_MORTALITY_PERCENTAGE;
	}
	
	public boolean isGameover()
	{
		return false;
	}
	
	public void newGame()
	{
		_kingdom.getTowns().clear();
		Town capital = new Town();
		capital.setCapital( true );
		capital.randomizePopulation();
		_kingdom.getTowns().add( capital );
	}
        
    // Update the game state   
    public void update()
	{
        _timeTillAgeAdvancement -= 1;
    	_kingdom.update( this );
        if( _timeTillAgeAdvancement == 0 )
		{
            _timeTillAgeAdvancement = AGE_ADVANCEMENT_DURATION;
		}
	}
        
    // Display any information needed this tick   
    public void display( self )
	{
    	
	}   
     
	// Get input from user
    public void handle_input()
	{
		boolean done = false;
		while( !done )
		{
			InputCommand cmd = _input.getInput();
			if( cmd == null )
			{
				System.out.println( "Unknown command" );
				_input.waitForEnter();
				continue;
			}
			if( cmd.perform( this ) )
				done = true;
		}
	}
	
	public boolean shouldQuit(){ return _quit; }
	public void setQuit( boolean q ){ _quit = q; }
	
    public Kingdom getKingdom(){ return _kingdom; }
	
	public int getTimeTillAgeAdvancement(){ return _timeTillAgeAdvancement; }
	public void setTimeTillAgeAdvancement( int v ){ _timeTillAgeAdvancement = v; }
	public void incTimeTillAgeAdvancement( int v ){ _timeTillAgeAdvancement += v; }
	
	public Map<PopulationType, Double> getDeathRate(){ return _deathRate; }
	
    public double getAdultProcreationRate(){ return _adultProcreationRate; }
	public void setAdultProcreationRate( double v ){ _adultProcreationRate = v; }
	
    public double getFertilityRate(){ return _fertilityRate; }
	public void setFertilityRate( double v ){ _fertilityRate = v; }
	
    public double getInUteroDeathRate(){ return _inUteroDeathRate; }
	public void setInUteroDeathRate( double v ){ _inUteroDeathRate = v; }
	
    public double getInfantMortalityRate(){ return _infantMortalityRate; }
	public void setInfantMortalityRate( double v ){ _infantMortalityRate = v; }
}
