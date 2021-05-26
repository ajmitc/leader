package leader;

import leader.clview.View;
import leader.clview.command.Command;
import leader.clview.command.CommandFactory;
import leader.clview.command.CommandType;

import java.util.Optional;
import java.util.logging.Logger;

public class ClController {
    private static Logger logger = Logger.getLogger(ClController.class.getName());

    private Model model;
    private View view;

    public ClController(Model model, View view){
        this.model = model;
        this.view = view;
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
            displayKingdomInfo();
        }
        else {
            for (Object arg : command.getArguments()) {
                String what = "" + arg;
                if (what.equalsIgnoreCase("kingdom")){
                    displayKingdomInfo();
                }
            }
        }
    }

    private void displayKingdomInfo(){
        view.print("The Royal Kingdom of ");
        view.println(model.getGame().getKingdom().getName());

        view.println("== Towns ==");
        for (Town town: model.getGame().getKingdom().getTowns()){
            view.print(town.getName());
            view.println(town.isCapital()? " (Capital)": "");
            view.print("  Location: ");
            view.println(town.getX() + ", " + town.getY());
            // TODO Display distance from capital to town
            view.print("  Size: ");
            view.println(town.getSqAcres() + " sq acres");
            view.print("  Population Size: ");
            view.println("" + town.getPopulation().size());
        }
    }


    private void handleSetCommand(Command command){
        if (command.getArguments().size() < 3){
            logger.warning("Invalid command.  Usage: rename <what> [<what>] <attribute> <new-value>.\n> rename kingdom name \"My Foobar\"\n> rename town \"Existing Town\" name \"New Town Name\"");
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
}
