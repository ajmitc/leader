package leader.clview.command;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private CommandType type;
    private List<Object> arguments = new ArrayList<>();

    public Command(){

    }

    public Command(CommandType type){
        this.type = type;
    }

    public Command(CommandType type, Object arg){
        this.type = type;
        arguments.add(arg);
    }

    public Command(CommandType type, List<Object> args){
        this.type = type;
        arguments.addAll(args);
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public List<Object> getArguments() {
        return arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }
}
