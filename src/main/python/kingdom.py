

class Kingdom( object ):
	def __init__( self ):
    	self.name = "Dalriada"
        self.size = 10 # square miles
        self.towns = []
        
        
    def update( self, game ):
        for town in self.towns:
            town.update( game )