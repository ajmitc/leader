package leader;

import leader.population.PersonAttribute;
import leader.population.Population;
import leader.population.PersonStruct;
import leader.util.Util;

import java.util.Map;
import java.util.logging.Logger;

public class Town {
    private Logger logger = Logger.getLogger(Town.class.getName());

    private String name;
    private Population population;
    private boolean capital;
    private int sqAcres;
    private int x, y;

    public Town() {
        name = "Dunoon";
        population = new Population();
        capital = false;
        sqAcres = 10;
        x = 0;
        y = 0;
        randomizePopulation();
    }

    public void randomizePopulation() {
        population.clear();
        population.addRandomPeople(Util.randInt(100, 500));
        sqAcres = population.size();
    }

    public void update(Game game) {
        if (game.getTimeTillAgeAdvancement() == 0)
            advancePopulationAge(game);
    }

    public void advancePopulationAge(Game game) {
        logInfo("Advancing Town Population Age");
        int deaths = 0;
        int numAdults = 0;

        // For each person
        // - Advance their age
        // - Check if they die
        for (byte[] person: population.getPersons()) {
            Population.advanceAge(person);
            Map<PersonStruct, PersonAttribute> person_attrs = Population.decode(person);

            // Check if person dies
            double death_rate = game.getDeathRate().get(person_attrs.get(PersonStruct.AGE));
            if (Util.rand() < death_rate) {
                // Yep, they have met their maker
                deaths += 1;
                Population.pack(0, person, PersonStruct.ALIVE);
                continue;
            }

            // Check if adult
            numAdults += (person_attrs.get(PersonStruct.AGE) == PersonAttribute.ADULT? 1: 0);
        }
        logInfo("Deaths: " + deaths);

        // Remove all dead people
        population.buryTheDead();

        // Determine number of new births

        // Each pair of adults will attempt to get pregnant
        int numAttemptedPregnancies = (int) (numAdults / 2.0 * game.getAdultProcreationRate());

        // Number of pregnancies is attempts / fertility rate
        int numPregnancies = (int) (numAttemptedPregnancies * game.getFertilityRate());
        logInfo("Num Pregnancies: " + numPregnancies);

        // Some births are terminated naturally
        int inUteroDeaths = (int) (numPregnancies * Game.IN_UTERO_DEATH_PERCENTAGE);
        logInfo("   Miscarriages/Still-Births: " + inUteroDeaths);

        // Some are still-births
        int infantMortalityRate = (int) (numPregnancies * Game.INFANT_MORTALITY_PERCENTAGE);
        logInfo("   Infant Mortalities: " + infantMortalityRate);

        int numBabies = numPregnancies - inUteroDeaths - infantMortalityRate;
        logInfo("   Babies added to population: " + numBabies);

        population.addRandomPeople(numBabies, 0);
        logInfo("Population Size: " + population.size());
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public Population getPopulation() {
        return population;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean v) {
        capital = v;
    }

    public int getSqAcres() {
        return sqAcres;
    }

    public void setSqAcres(int s) {
        sqAcres = s;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void logInfo(String msg){
        logger.info("[" + getName() + "] " + msg);
    }

    public void logWarn(String msg){
        logger.warning("[" + getName() + "] " + msg);
    }

    public void logError(String msg){
        logger.severe("[" + getName() + "] " + msg);
    }

    public String toString(){
        return name;
    }
}