package leader.game.event;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GameEventQueueThread extends SwingWorker<Void, Void> {
    private static Logger logger = Logger.getLogger(GameEventQueueThread.class.getName());

    private GameEventQueue gameEventQueue;
    private List<GameEventListener> listeners = new ArrayList<>();

    public GameEventQueueThread(GameEventQueue queue){
        super();
        this.gameEventQueue = queue;
    }

    @Override
    protected Void doInBackground() throws Exception {
        logger.info("Starting GameEventQueueThread");
        while (!isCancelled()){
            GameEvent event = gameEventQueue.pop();
            if (event != null){
                synchronized (GameEventQueueThread.class) {
                    for (GameEventListener listener : listeners) {
                        listener.handleGameEvent(event);
                        if (event.isConsumed())
                            break;
                    }
                }
            }
            while (!isCancelled() && gameEventQueue.isEmpty()) {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException ie){

                }
            }
        }
        return null;
    }

    public void addListener(GameEventListener listener){
        synchronized (GameEventQueueThread.class) {
            if (!listeners.contains(listener))
                listeners.add(listener);
        }
    }

    public void removeListener(GameEventListener listener){
        synchronized (GameEventQueueThread.class) {
            if (listeners.contains(listener))
                listeners.remove(listener);
        }
    }
}
