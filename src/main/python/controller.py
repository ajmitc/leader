
class Controller( object ):
	def __init__( self, model ):
    	self.model = model
		self.game = model.game
		
        
	def start( self ):
		self.print_welcome()
		self.gameloop()
        
        
	def print_welcome( self ):
		print "After many days and nights on the road, you and your band of followers arrive at the site of your new great land. You settle on 10 square acres."
		print "You are the Leader!"
		print "Type help at anytime"

	def gameloop( self ):
		if self.game is None:
			print "Game is None, cannot enter game loop"
			return
		while not self.game.is_gameover() and not self.game.quit:
			self.game.update()
            self.game.display()
            self.game.handle_input()