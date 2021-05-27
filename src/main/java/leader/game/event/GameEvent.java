package leader.game.event;

public class GameEvent {
    private GameEventType type;
    private Object source;
    private Object data;

    // If consumed == true, do not continue sending it to listeners
    private boolean consumed = false;

    public GameEvent(GameEventType type, Object source, Object data){
        this.type = type;
        this.source = source;
        this.data = data;
    }

    public GameEventType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public Object getSource() {
        return source;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void consume() {
        this.consumed = true;
    }
}
