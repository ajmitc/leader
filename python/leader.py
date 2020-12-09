from model import Model
from controller import Controller

class Leader( object ):
	def __init__( self ):
		self.model = Model() 
		self.controller = Controller()
        
        
	def mainmenu( self ):
    	while True:
        	print "Leader"
            print "N) New Game"
            print "C) Continue Game"
            print "Q) Quit"
            inp = raw_input( "> " ).strip().lower()
            if inp == "Q":
            	break
            elif inp == "N":
            	self.newgame()
            elif inp == "C":
            	self.continue_game()
            else:
            	print "Unsupported option"
                
                
    def newgame( self ):
    	self.controller.start()
        
        
    def continue_game( self ):
    	pass
        
        
if __name__ == "__main__":
	leader = Leader()
    leader.mainmenu()
    