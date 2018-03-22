package leader;

import leader.population.Population;
import leader.util.Util;

import java.util.List;
import java.util.ArrayList;

public class Town
{
	private String _name;
	private Population _population;
	private boolean _capital;
	private int _sqAcres;
	private int _x, _y;
	
	public Town()
	{
    	_name = "My Capital";
        _population = new Population();
		_capital = false;
		_sqAcres = 10;
		_x = 0;
		_y = 0;
	}
	
	public void randomizePopulation()
	{
		_population.clear();
		_population.addRandomPeople( Util.randInt( 100, 500 ) );
		_sqAcres = _population.size();
	}
        
    public void update( Game game )
	{
        if( game.getTimeTillAgeAdvancement() == 0 )
            advancePopulationAge( game );
	}
            
    public void advancePopulationAge( Game game )
	{
        List<Integer> deaths = new ArrayList<>();
        int num_adults = 0;
        for( Integer person: _population )
		{
            Population.advanceAge( person );
            Map<PopulationType, PopulationType> person_attrs = Population.decode( person );
            // Check if person dies
            double death_rate = game.getDeathRate[ person_attrs[ Population.AGE ] ]
            if random.random() < death_rate:
                # Yep, they have met their maker
                deaths.append( person )
                continue
            # Check if adult
            num_adults += 1 if person_attrs[ Population.AGE ] == Population.ADULT else 0
		}
		num_attempted_pregnancies = int(float(num_adults) / 2.0 * game.adult_procreation_rate)
        num_pregnancies = float(num_attempted_pregnancies) * game.fertility_rate
        
        
        self.fertility_rate = self.FERTILITY_PERCENTAGE
        self.in_utero_death_rate = self.IN_UTERO_DEATH_PERCENTAGE
        self.infant_mortality_rate = self.INFANT_MORTALITY_PERCENTAGE
	}
	
	public String getName(){ return _name; }
	public void setName( String n ){ _name = n; }
	
	public Population getPopulation(){ return _population; }
	
	public boolean isCapital(){ return _capital; }
	public void setCapital( boolean v ){ _capital = v; }
	
	public int getSqAcres(){ return _sqAcres; }
	public void setSqAcres( int s ){ _sqAcres = s; }
	
	public int getX(){ return _x; }
	public int getY(){ return _y; }
	public void setCoord( int x, int y ){ _x = x; _y = y; }
}