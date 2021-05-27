package leader.game.grievance;

import leader.Model;
import leader.game.Town;
import leader.game.event.GameEvent;
import leader.game.event.GameEventGeneratorIF;
import leader.game.event.GameEventType;
import leader.game.population.PersonAttribute;
import leader.util.Util;

import java.util.logging.Logger;

public class GrievanceEventGenerator implements GameEventGeneratorIF {
    private Logger logger = Logger.getLogger(GrievanceEventGenerator.class.getName());

    private double probability = 0.1;

    @Override
    public boolean shouldGenerateEvent(Model model, long now) {
        return Util.rand() < probability;
    }

    @Override
    public GameEvent generateEvent(Model model, long now) {
        // Choose Town
        Town town = model.getGame().getKingdom().getTowns().get(Util.randInt(model.getGame().getKingdom().getTowns().size()));
        // Choose person
        byte[] person = town.getPopulation().findRandomPerson(PersonAttribute.ADULT, PersonAttribute.MALE);

        Grievance grievance = generateGrievance(person, town);
        GameEvent gameEvent = new GameEvent(GameEventType.NEW_GRIEVANCE, this, grievance);
        return gameEvent;
    }

    private Grievance generateGrievance(byte[] person, Town town){
        byte[] otherPerson = null;
        do {
            otherPerson = town.getPopulation().findRandomPerson(PersonAttribute.ADULT, PersonAttribute.MALE);
        }while (otherPerson == person);
        Grievance grievance = new Grievance(person, town, "Property Dispute", "The other person took MY stuff (which I found in the road in front of his house)!", otherPerson, town);
        logger.info("Generated Grievance");
        return grievance;
    }
}
