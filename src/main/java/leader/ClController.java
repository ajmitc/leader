package leader;

import leader.clview.View;
import leader.clview.command.Command;
import leader.clview.command.CommandFactory;
import leader.clview.command.CommandType;
import leader.game.event.GameEvent;
import leader.game.event.GameEventListener;
import leader.game.event.GameEventType;
import leader.game.Town;
import leader.game.grievance.Grievance;
import leader.game.grievance.GrievanceResolution;
import leader.game.population.PersonAttribute;
import leader.game.population.Population;
import leader.game.population.PopulationStatistics;
import leader.util.Util;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class ClController implements GameEventListener {
    private static Logger logger = Logger.getLogger(ClController.class.getName());

    private Model model;
    private View view;

    public ClController(Model model, View view){
        this.model = model;
        this.view = view;

        this.model.addGameEventListener(this);
    }

    public void run(){
        model.newGame();
        while (!model.getGame().isGameover()){
            view.print("> ");
            String input = view.getUserInput();
            Command command = CommandFactory.parse(input);
            if (command == null){
                continue;
            }
            handleCommand(command);
        }
    }

    private void handleCommand(Command command){
        switch (command.getType()){
            case EXIT -> handleExitCommand();
            case HELP -> handleHelpCommand(command);
            case INFO -> handleInfoCommand(command);
            case SET  -> handleSetCommand(command);
            case GRIEVANCES -> handleGrievances(command);
        }
    }

    private void handleExitCommand(){
        System.exit(0);
    }


    private void handleHelpCommand(Command command){
        int longestCommandName = 0;
        for (CommandType type: CommandType.values()){
            int length = type.getAllowedInputString().length();
            if (length > longestCommandName)
                longestCommandName = length;
        }
        longestCommandName += 3;

        for (CommandType type: CommandType.values()){
            view.println(type.getHelp(longestCommandName));
        }
    }

    private void handleInfoCommand(Command command){
        if (command.getArguments().isEmpty()){
            // Display all info
            displayKingdomInfoSummary();
        }
        else {
            for (int i = 0; i < command.getArguments().size(); ++i) {
                Object arg = command.getArguments().get(i);
                String what = "" + arg;
                if (what.equalsIgnoreCase("kingdom")){
                    displayKingdomInfoDetails();
                }
                else if (what.equalsIgnoreCase("town")){
                    if (i < command.getArguments().size() - 1){
                        String townName = "" + command.getArguments().get(i + 1);
                        displayTownInfoDetails(townName);
                    }
                }
                else if (what.equalsIgnoreCase("game")){
                    displayGameInfo();
                }
            }
        }
    }

    private void displayGameInfo(){
        view.println("#########################################################################");
        view.println("Total Ticks: " + model.getGame().getTotalTicks());
        view.println("Time till Age Advancement: " + model.getGame().getTimeTillAgeAdvancement());
        view.println("== New Population Rates ==");
        view.println("  Adult Procreation Rate:  " + model.getGame().getAdultProcreationRate() + " (% of adults that attempt to procreate)");
        view.println("  Fertility Rate:          " + model.getGame().getFertilityRate() + " (% success of procreation)");
        view.println("== Early Mortality Rates ==");
        view.println("  In-utero Mortality Rate: " + model.getGame().getInUteroDeathRate() + " (% of lost pregnancies)");
        view.println("  Infant Mortality Rate:   " + model.getGame().getInfantMortalityRate() + " (% of children lost during infancy)");
        view.println("== Death Rates (% chance of death) ==");
        view.println("  Elderly Death Rate:      " + model.getGame().getDeathRate().get(PersonAttribute.ELDERLY));
        view.println("  Adult Death Rate:        " + model.getGame().getDeathRate().get(PersonAttribute.ADULT));
        view.println("  Adolescent Death Rate:   " + model.getGame().getDeathRate().get(PersonAttribute.ADOLESCENT));
        view.println("  Child Death Rate:        " + model.getGame().getDeathRate().get(PersonAttribute.CHILD));
        view.println("#########################################################################");
    }

    private void displayKingdomInfoSummary() {
        displayKingdomInfoDetails();
    }

    private void displayKingdomInfoDetails(){
        view.print("The Royal Kingdom of ");
        view.println(model.getGame().getKingdom().getName());

        int totalPopulationSize = 0;
        int totalSize = 0;
        double farthestDistance = 0.0;
        Town farthestTown = null;
        view.println("== Towns (" + model.getGame().getKingdom().getTowns().size() + ") ==");
        for (Town town : model.getGame().getKingdom().getTowns()) {
            view.print(town.getName());
            view.println(town.isCapital() ? " (Capital)" : "");
            double distance = Util.getDistance(0, 0, town.getX(), town.getY());
            if (farthestTown == null || distance > farthestDistance){
                farthestDistance = distance;
                farthestTown = town;
            }
            view.println("  Location: " + town.getX() + ", " + town.getY() + (town.isCapital() ? "" : " (" + distance + " miles from capital)"));
            view.println("  Size: " + town.getSqAcres() + " sq acres");
            view.println("  Population Size: " + town.getPopulation().size());
            totalPopulationSize += town.getPopulation().size();
            totalSize += town.getSqAcres();
        }

        view.println("Total Size:            " + totalSize + " sq acres");
        view.println("Total Population Size: " + totalPopulationSize);
        view.println("Farthest Town: " + farthestTown.getName() + " (" + farthestDistance + " miles away)");

        view.println("\n== Management ==");
        view.println("Grievances: " + model.getGame().getGrievances().size());
    }


    private void displayTownInfoDetails(String townName){
        Town town = model.getGame().getKingdom().getTown(townName);
        if (town == null){
            logger.warning("Unable to find town with that name");
            return;
        }

        view.println(town.getName());
        double distance = Util.getDistance(0, 0, town.getX(), town.getY());
        view.println("  Location: " + town.getX() + ", " + town.getY() + (town.isCapital()? "": " (" + distance + " miles from capital)"));
        view.println("  Size: " + town.getSqAcres() + " sq acres");
        view.println("  Population Statistics:");
        PopulationStatistics statistics = town.getPopulation().generateStats();
        Map<PersonAttribute, Double> stats = statistics.getStats();
        for (PersonAttribute personAttribute: PopulationStatistics.STAT_ORDER) {
            view.print("    " + personAttribute.toString());
            view.println(": " + stats.get(personAttribute));
        }
        view.println("  Population Size: " + town.getPopulation().size());
    }


    private void handleSetCommand(Command command){
        if (command.getArguments().size() < 3){
            logger.warning("Invalid command.  Usage: set <what> [<what>] <attribute> <new-value>.\n> set kingdom name \"My Foobar\"\n> set town \"Existing Town\" name \"New Town Name\"");
            return;
        }

        String what = "" + command.getArguments().get(0);
        if (what.equalsIgnoreCase("kingdom")){
            String attr = "" + command.getArguments().get(1);
            if (attr.equalsIgnoreCase("name")){
                String value = "" + command.getArguments().get(2);
                model.getGame().getKingdom().setName(value);
            }
            else
                logger.warning("Unable to set that attribute at this time");
        }
        else if (what.equalsIgnoreCase("town")){
            String townName = "" + command.getArguments().get(1);
            Optional<Town> optTown = model.getGame().getKingdom().getTowns().stream().filter(town -> town.getName().equalsIgnoreCase(townName)).findFirst();
            if (optTown.isPresent()) {
                String attr = "" + command.getArguments().get(2);
                if (attr.equalsIgnoreCase("name")){
                    if (command.getArguments().size() >= 4) {
                        String value = "" + command.getArguments().get(3);
                        optTown.get().setName(value);
                    }
                    else
                        logger.warning("Rename town to what?");
                }
                else
                    logger.warning("Unable to set that attribute at this time");
            }
            else
                logger.warning("Unable to find town '" + townName + "'");
        }
        else
            logger.warning("Unable to rename a '" + what + "'");
    }


    private void handleGrievances(Command command){
        view.println("Grievance (" + model.getGame().getGrievances().size() + " left):");
        if (!model.getGame().getGrievances().isEmpty()) {
            Grievance grievance = model.getGame().getGrievances().get(0);
            view.println(grievance.getSummary());
            view.println("An " + Population.summarizePerson(grievance.getPerson()) + " approaches:");
            view.println(grievance.getDescription());
            // TODO Let user select resolution and apply
            for (int i = 0; i < grievance.getPossibleResolutions().size(); ++i){
                GrievanceResolution resolution = grievance.getPossibleResolutions().get(i);
                view.println(resolution.getDescription());
            }
        }
    }

    @Override
	public void handleGameEvent(GameEvent gameEvent) {
		switch (gameEvent.getType()){
            case ADVANCE_TICK: {
                if (this.model.getGame() != null)
                    this.model.getGame().update();
                gameEvent.consume();
                break;
            }
            case NEW_GRIEVANCE:{
                Grievance grievance = (Grievance) gameEvent.getData();
                model.getGame().getGrievances().add(grievance);
                break;
            }
		}
	}
}
