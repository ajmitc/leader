package leader.clview.command;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CommandType {
    EXIT(new String[]{"q", "exit", "quit"}, "Exit the program"),

    // ?, help
    HELP(new String[]{"?", "help"}, "Generate this help message"),

    // info [kingdom]
    INFO(new String[]{"i", "info"}, "Display information about the game"),

    // set <what> [<what>] <attr> <value>
    // ie. > set kingdom name "My Kingdom"
    // ie. > set town "Existing Town" name "New Name"
    SET(new String[]{"set, rename"}, "Set/rename an attribute in the game")
    ;

    private String[] allowedInput;
    private String description;
    CommandType(String[] allowedInput, String description){
        this.allowedInput = allowedInput;
        this.description = description;
    }

    public String[] getAllowedInput() {
        return allowedInput;
    }

    public String getDescription() {
        return description;
    }

    public String getAllowedInputString(){
        return Arrays.stream(allowedInput).collect(Collectors.joining(", "));
    }

    public String getHelp(int spacing){
        StringBuilder sb = new StringBuilder(getAllowedInputString());
        spacing -= sb.length();
        for (int i = 0; i < spacing; ++i)
            sb.append(" ");
        sb.append(description);
        return sb.toString();
    }

    public String toString(){
        return name();
    }
}
