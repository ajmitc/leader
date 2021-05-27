package leader.game.event;

import leader.Model;

public interface GameEventGeneratorIF {
    boolean shouldGenerateEvent(Model model, long now);
    GameEvent generateEvent(Model model, long now);
}
