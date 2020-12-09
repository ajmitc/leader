package leader;

import leader.event.*;

import java.util.logging.Logger;

public class Model implements GameEventProducer {
	private static Logger logger = Logger.getLogger(Model.class.getName());

	// Number of real-time seconds between tick advancement
	// The Game object measures time in ticks (1 tick == 1 month)
	public static final int MILLISECONDS_PER_TICK = 1000;

	private Game game;
	private GameEventQueue gameEventQueue;
	private GameEventQueueThread gameEventQueueThread;
	private GameEventGenerator gameEventGenerator;

	public Model(){
		this.gameEventQueue = new GameEventQueue();
		this.gameEventQueueThread = new GameEventQueueThread(this.gameEventQueue);
		this.gameEventQueueThread.execute();

		this.gameEventGenerator = new GameEventGenerator(this);
		this.gameEventGenerator.execute();
	}

	public void stop(){
		this.gameEventQueueThread.cancel(true);
	}
	
	public void newGame(){
		game = new Game(this);
	}

	public Game getGame(){return game;}

	public void publishGameEvent(GameEvent event){
	    this.gameEventQueue.publish(event);
    }

	public void addGameEventListener(GameEventListener listener){
		this.gameEventQueueThread.addListener(listener);
	}

	public void removeGameEventListener(GameEventListener listener){
		this.gameEventQueueThread.removeListener(listener);
	}
}