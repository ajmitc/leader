package leader.event;

public class GameEvent {
    public static final int TYPE_ADVANCE_TICK = 0;

    private int type;
    private Object source;
    private Object data;

    // If consumed == true, do not continue sending it to listeners
    private boolean consumed = false;

    public GameEvent(int type, Object source, Object data){
        this.type = type;
        this.source = source;
        this.data = data;
    }

    public int getType() {
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
