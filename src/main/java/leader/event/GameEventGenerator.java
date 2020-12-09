package leader.event;

import leader.Model;

import javax.swing.*;
import java.util.Date;
import java.util.logging.Logger;

/**
 * This class runs as a thread and
 */
public class GameEventGenerator extends SwingWorker<Void, Void> {
    private static Logger logger = Logger.getLogger(GameEventGenerator.class.getName());

    private Model model;
    private long nextTickAdvancementEpochSeconds = 0L;

    public GameEventGenerator(Model model){
        super();
        this.model = model;
        nextTickAdvancementEpochSeconds = new Date().getTime() + Model.MILLISECONDS_PER_TICK;
    }

    @Override
    protected Void doInBackground() throws Exception {
        logger.info("Starting GameEventGenerator");
        while (!isCancelled()){
            long now = new Date().getTime();
            if (shouldGenerateEvent(now)) {
                GameEvent gameEvent = generateEvent(now);
                //logger.info("Publishing GameEvent " + gameEvent.getType());
                model.publishGameEvent(gameEvent);
            }
            if (!isCancelled()) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
        logger.info("Exiting doInBackground()");
        return null;
    }

    private boolean shouldGenerateEvent(long now){
        if (now >= nextTickAdvancementEpochSeconds){
            //logger.info("Yes event");
            return true;
        }
        //logger.info("No event");
        return false;
    }

    private GameEvent generateEvent(long now){
        if (now >= nextTickAdvancementEpochSeconds){
            nextTickAdvancementEpochSeconds = now + Model.MILLISECONDS_PER_TICK;
            //logger.info("Set tick advancement to " + nextTickAdvancementEpochSeconds);
            return new GameEvent(GameEvent.TYPE_ADVANCE_TICK, this, null);
        }
        return null;
    }
}
