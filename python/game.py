from input.input import Input
from kingdom import Kingdom

class Game( object ):
    TICKS_PER_YEAR = 12
    
    AGE_ADVANCEMENT_DURATION = TICKS_PER_YEAR * 10
    
    ELDERLY_DEATH_PERCENTAGE = 0.9 # Percent chance an elderly person dies  (higher percentage means shorter lifespan)
    ADULT_DEATH_PERCENTAGE   = 0.1 # Percent chance an adult dies
    ADOLESCENT_DEATH_PERCENTAGE = 0.05 # Percent chance an adolescent dies
    CHILD_DEATH_PERCENTAGE = 0.1
    
    ADULT_PROCREATION_PERCENTAGE = 0.8  # Percentage of adult population trying to procreate
    FERTILITY_PERCENTAGE = 0.1  # Likelihood that an adult couple will conceive a child
    IN_UTERO_DEATH_PERCENTAGE = 0.1 # Likelihood that a pregnancy will abort or end in still-birth
    INFANT_MORTALITY_PERCENTAGE = 0.1  # Likelihood a newborn will die (evaluated immediately after birth)
    
	def __init_( self ):
        self.quit = False
		self.input = Input()
        self.kingdom = Kingdom()
		self.time_till_age_advancement = self.AGE_ADVANCEMENT_DURATION
	    self.death_rate = {
            Population.ELDERLY: self.ELDERLY_DEATH_PERCENTAGE,
            Population.ADULT:   self.ADULT_DEATH_PERCENTAGE,
            Population.ADOLESCENT: self.ADOLESCENT_DEATH_PERCENTAGE,
            Population.CHILD: self.CHILD_DEATH_PERCENTAGE
        }
        self.adult_procreation_rate = self.ADULT_PROCREATION_PERCENTAGE
        self.fertility_rate = self.FERTILITY_PERCENTAGE
        self.in_utero_death_rate = self.IN_UTERO_DEATH_PERCENTAGE
        self.infant_mortality_rate = self.INFANT_MORTALITY_PERCENTAGE
    
	def is_gameover( self ):
		return False
        
        
    def update( self ):
		""" Update the game state """
        self.time_till_age_advancement -= 1
    	self.kingdom.update( self )
        if self.time_till_age_advancement == 0:
            self.tiem_till_age_advancement = self.AGE_ADVANCEMENT_DURATION
        
        
    def display( self ):
		""" Display any information needed this tick """
    	pass
        
        
    def handle_input( self ):
		""" Get input from user """
		done = False
		while not done:
			cmd_or_inp = self.input.get_input()
			if isinstance( cmd_or_inp, str ):
				print "Unknown command: '%s'" % cmd_or_inp
				self.input.wait_for_enter()
				continue
			if cmd_or_inp.perform( self ):
				done = True
        
        