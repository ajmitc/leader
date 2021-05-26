package leader.clview.command;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFactory {
    private static Logger logger = Logger.getLogger(CommandFactory.class.getName());

    public static Command parse(String line){
        List<String> tokens = splitLine(line);
        if (tokens.isEmpty())
            return null;
        String cmd = tokens.get(0);
        for (CommandType commandType: CommandType.values()){
            for (String allowedInput: commandType.getAllowedInput()){
                if (cmd.equalsIgnoreCase(allowedInput)){
                    return constructCommand(commandType, tokens);
                }
            }
        }
        logger.warning("Unsupported command '" + cmd + "'");
        return null;
    }

    public static Command constructCommand(CommandType type, List<String> tokens){
        switch (type){
            // TODO Add special handling of commands here
            default:
                return populateCommand(type, tokens);
        }
    }

    private static Command populateCommand(CommandType type, List<String> tokens){
        Command command = new Command(type);
        for (String token: tokens.subList(1, tokens.size())){
            command.getArguments().add(token);
        }
        return command;
    }

    public static List<String> splitLine(String line){
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);
        while (m.find())
            list.add(m.group(1).replace("\"", ""));// remove surrounding quotes.
        return list;
    }

    private CommandFactory(){}
}
