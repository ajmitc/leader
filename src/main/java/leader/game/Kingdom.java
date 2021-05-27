package leader.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Kingdom {
    private String name;
    private List<Town> towns;

    public Kingdom() {
        name = "Dalriada";
        towns = new ArrayList<>();
        createCapital();
    }


    public void update(Game game) {
        for (Town town : towns) {
            town.update(game);
        }
    }

    public int getPopulationSize() {
        int total = 0;
        for (Town town : towns) {
            total += town.getPopulation().size();
        }
        return total;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    /**
     * Return the square acres of the Kingdom.  For now, this is the sum total of all town sq acres.
     */
    public int getSqAcres() {
        int total = 0;
        for (Town town : towns) {
            total += town.getSqAcres();
        }
        // TODO Add in sq acres between towns
        return total;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public Town getTown(String name){
        Optional<Town> opt = towns.stream().filter(town -> town.getName().equalsIgnoreCase(name)).findFirst();
        return opt.isPresent()? opt.get(): null;
    }

    public Town getCapital(){
        Optional<Town> capital = towns.stream().filter(t -> t.isCapital()).findFirst();
        return capital.isPresent()? capital.get(): null;
    }

    private void createCapital(){
        if (getCapital() != null)
            return;
        Town capital = foundTown();
        capital.setCapital(true);
        capital.randomizePopulation();
    }

    public Town foundTown(){
        Town town = new Town();
        towns.add(town);
        return town;
    }
}