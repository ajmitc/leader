

class Command( object ):
	""" This class represents parsed user input """
	def __init__( self ):
        from kingdom_commands import *
		self.all_commands = [
			DisplayHelpCommand(),
            KingdomSummaryCommand(),
		]
		
		
	def parse( self, inp ):
		for cmd in self.all_commands:
			inst = cmd.parse( inp )
			if inst is not None:
				return inst
		return None
	
    
	def display_help( self ):
		""" Display help information """
		print "Leader Help"
		for cmd in self.all_commands:
			cmd.display_help()
            
            
    def perform( self, game ):
        """ Subclasses should override this method.  Return True if the command was handled properly, False otherwise. """
        return False
			
	
	
	
class DisplayHelpCommand( Command ):
	def __init__( self ):
		pass
	
    
	def parse( self, inp ):
		parts = inp.split( " " )
		if parts[ 0 ].lower() in [ 'help', '?' ]:
			return DisplayHelpCommand()
		return None
	
    
	def perform( self, game ):
		super( self, Command ).display_help()
		return True
	
	
	def display_help( self ):
		print "	help, ?			Display this help information"
	