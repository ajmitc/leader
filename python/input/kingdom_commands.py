from command import Command

class KingdomSummaryCommand( Command ):
    def __init__( self ):
        Command.__init__( self )
        
        
    def perform( self, game ):
        print "Kingdom Summary"
        print "Name: %s" % game.kingdom.name
        print "Size: %d sq. acres" % game.kingdom.size
        print "Towns:"
        for town in game.kingdom.towns:
            print "   %s [Pop: %d]" % (town.name, len(town.population))
    
    
    def parse( self, inp ):
        parts = inp.split( " " )
		if parts[ 0 ].lower() in [ 'summary', 'kingdom' ]:
			return KingdomSummaryCommand()
		return None
    
    
    def display_help( self ):
		print "	summary, kingdom			Display kingdom summary"
        