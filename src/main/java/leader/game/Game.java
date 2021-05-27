package leader.game;

import leader.game.event.GameEventProducer;
import leader.game.grievance.Grievance;
import leader.game.population.PersonAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Game{
    private static Logger logger = Logger.getLogger(Game.class.getName());

    public static final int TICKS_PER_YEAR = 12;

    public static final int AGE_ADVANCEMENT_DURATION = TICKS_PER_YEAR * 10;

    public static final double ELDERLY_DEATH_PERCENTAGE = 0.5;  // Percent chance an elderly person dies  (higher percentage means shorter lifespan)
    public static final double ADULT_DEATH_PERCENTAGE = 0.02;  // Percent chance an adult dies
    public static final double ADOLESCENT_DEATH_PERCENTAGE = 0.01; // Percent chance an adolescent dies
    public static final double CHILD_DEATH_PERCENTAGE = 0.01;

    public static final double ADULT_PROCREATION_PERCENTAGE = 0.8; // Percentage of adult population trying to procreate
    public static final double FERTILITY_PERCENTAGE = 0.1; // Likelihood that an adult couple will conceive a child
    public static final double IN_UTERO_DEATH_PERCENTAGE = 0.20; // Likelihood that a pregnancy will abort or end in still-birth
    public static final double INFANT_MORTALITY_PERCENTAGE = 0.10; // Likelihood a newborn will die (evaluated immediately after birth)

    private boolean gameover;
    private GameEventProducer gameEventProducer;
    // Total number of ticks we've progressed through in the game
    // 1 tick == 1 month in game time
    private int totalTicks;
    private Kingdom kingdom;
    private int timeTillAgeAdvancement;
    private Map<PersonAttribute, Double> deathRate;
    private double adultProcreationRate;
    private double fertilityRate;
    private double inUteroDeathRate;
    private double infantMortalityRate;

    private List<Grievance> grievances = new ArrayList<>();

    public Game(GameEventProducer producer) {
        gameover = false;
        this.gameEventProducer = producer;
        totalTicks = 0;
        kingdom = new Kingdom();
        timeTillAgeAdvancement = AGE_ADVANCEMENT_DURATION;
        deathRate = new HashMap<>();
        deathRate.put(PersonAttribute.ELDERLY, ELDERLY_DEATH_PERCENTAGE);
        deathRate.put(PersonAttribute.ADULT, ADULT_DEATH_PERCENTAGE);
        deathRate.put(PersonAttribute.ADOLESCENT, ADOLESCENT_DEATH_PERCENTAGE);
        deathRate.put(PersonAttribute.CHILD, CHILD_DEATH_PERCENTAGE);
        adultProcreationRate = ADULT_PROCREATION_PERCENTAGE;
        fertilityRate = FERTILITY_PERCENTAGE;
        inUteroDeathRate = IN_UTERO_DEATH_PERCENTAGE;
        infantMortalityRate = INFANT_MORTALITY_PERCENTAGE;
        init();
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    private void init() {

    }

    // Update the game state   
    public void update() {
        totalTicks += 1;
        timeTillAgeAdvancement -= 1;
        kingdom.update(this);
        if (timeTillAgeAdvancement == 0) {
            timeTillAgeAdvancement = AGE_ADVANCEMENT_DURATION;
        }
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public int getTotalTicks() {
        return totalTicks;
    }

    public int getTimeTillAgeAdvancement() {
        return timeTillAgeAdvancement;
    }

    public void setTimeTillAgeAdvancement(int v) {
        timeTillAgeAdvancement = v;
    }

    public void incTimeTillAgeAdvancement(int v) {
        timeTillAgeAdvancement += v;
    }

    public Map<PersonAttribute, Double> getDeathRate() {
        return deathRate;
    }

    public double getAdultProcreationRate() {
        return adultProcreationRate;
    }

    public void setAdultProcreationRate(double v) {
        adultProcreationRate = v;
    }

    public double getFertilityRate() {
        return fertilityRate;
    }

    public void setFertilityRate(double v) {
        fertilityRate = v;
    }

    public double getInUteroDeathRate() {
        return inUteroDeathRate;
    }

    public void setInUteroDeathRate(double v) {
        inUteroDeathRate = v;
    }

    public double getInfantMortalityRate() {
        return infantMortalityRate;
    }

    public void setInfantMortalityRate(double v) {
        infantMortalityRate = v;
    }

    public List<Grievance> getGrievances() {
        return grievances;
    }

    public void setGrievances(List<Grievance> grievances) {
        this.grievances = grievances;
    }
}
