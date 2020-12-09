

class Input( object ):
	def __init__( self ):
		self.command = Command()
	
	
	def get_input( self, prompt="> " ):
		inp = raw_input( prompt ).strip()
		# attempt to parse it and construct command, if unable, return raw input
		cmd = self.parse( inp )
		return inp if cmd is None else cmd
	
	
	def parse( self, inp ):
		cmdobj = self.command.parse( inp )
        return cmdobj
	