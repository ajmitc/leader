package leader.game.population;

import leader.Model;
import leader.game.event.GameEvent;
import leader.game.event.GameEventGeneratorIF;
import leader.game.event.GameEventType;

import java.util.Date;

public class AgeAdvancementEventGenerator implements GameEventGeneratorIF {
    private long nextTickAdvancementEpochSeconds = 0L;

    public AgeAdvancementEventGenerator(){
        nextTickAdvancementEpochSeconds = new Date().getTime() + Model.MILLISECONDS_PER_TICK;
    }

    public boolean shouldGenerateEvent(Model model, long now){
        if (now >= nextTickAdvancementEpochSeconds){
            //logger.info("Yes event");
            return true;
        }
        //logger.info("No event");
        return false;
    }

    public GameEvent generateEvent(Model model, long now){
        if (now >= nextTickAdvancementEpochSeconds){
            nextTickAdvancementEpochSeconds = now + Model.MILLISECONDS_PER_TICK;
            //logger.info("Set tick advancement to " + nextTickAdvancementEpochSeconds);
            return new GameEvent(GameEventType.ADVANCE_TICK, this, null);
        }
        return null;
    }
}
