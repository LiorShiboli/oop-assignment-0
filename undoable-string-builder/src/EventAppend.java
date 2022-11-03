package assignmentzero
public class EventAppend implements Event{
    final static EventType type = EventType.APPEND;
    private int oldEnd;
    public EventType getEventType();
        {
            return type;
        }

    public int getOldEnd() {
        return oldEnd;
    }

    public void setOldEnd(int oldEnd) {
        this.oldEnd = oldEnd;
    }
}
