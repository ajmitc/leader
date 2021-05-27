package leader.game.event;

import leader.Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class runs as a thread and
 */
public class GameEventGenerator extends SwingWorker<Void, Void> {
    private static Logger logger = Logger.getLogger(GameEventGenerator.class.getName());

    private Model model;
    private List<GameEventGeneratorIF> gameEventGenerators = new ArrayList<>();

    public GameEventGenerator(Model model){
        super();
        this.model = model;
    }

    @Override
    protected Void doInBackground() throws Exception {
        logger.info("Starting GameEventGenerator");
        while (!isCancelled()){
            long now = new Date().getTime();
            for (GameEventGeneratorIF gameEventGenerator: gameEventGenerators) {
                if (gameEventGenerator.shouldGenerateEvent(model, now)) {
                    GameEvent gameEvent = gameEventGenerator.generateEvent(model, now);
                    //logger.info("Publishing GameEvent " + gameEvent.getType());
                    model.publishGameEvent(gameEvent);
                }
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

    public void addGameEventGenerator(GameEventGeneratorIF gameEventGeneratorIF){
        gameEventGenerators.add(gameEventGeneratorIF);
    }
}
