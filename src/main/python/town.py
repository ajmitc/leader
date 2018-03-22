from population import Population

class Town( object ):
	def __init__( self ):
    	self.name = "Dalriada"
        self.population = []  # List of unsigned char strings
        
        
    def update( self, game ):
        if game.time_till_age_advancement == 0:
            self.advance_population_age( game )
            
            
    def advance_population_age( self, game ):
        deaths = []
        num_adults = 0
        for person in self.population:
            Population.advance_age( person )
            person_attrs = Population.decode( person )
            # Check if person dies
            death_rate = game.death_rate[ person_attrs[ Population.AGE ] ]
            if random.random() < death_rate:
                # Yep, they have met their maker
                deaths.append( person )
                continue
            # Check if adult
            num_adults += 1 if person_attrs[ Population.AGE ] == Population.ADULT else 0
        num_attempted_pregnancies = int(float(num_adults) / 2.0 * game.adult_procreation_rate)
        num_pregnancies = float(num_attempted_pregnancies) * game.fertility_rate
        
        
        self.fertility_rate = self.FERTILITY_PERCENTAGE
        self.in_utero_death_rate = self.IN_UTERO_DEATH_PERCENTAGE
        self.infant_mortality_rate = self.INFANT_MORTALITY_PERCENTAGE