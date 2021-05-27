package leader.game.event;

import java.util.LinkedList;
import java.util.logging.Logger;

public class GameEventQueue {
    private static Logger logger = Logger.getLogger(GameEventQueue.class.getName());

    private LinkedList<GameEvent> events = new LinkedList<>();

    public GameEventQueue(){

    }

    public void publish(GameEvent event){
        synchronized (GameEventQueue.class) {
            events.add(event);
        }
    }

    public GameEvent pop(){
        synchronized (GameEventQueue.class){
            if (!events.isEmpty()){
                return events.pop();
            }
        }
        return null;
    }

    public int size(){
        return events.size();
    }

    public boolean isEmpty(){
        return events.isEmpty();
    }
}
