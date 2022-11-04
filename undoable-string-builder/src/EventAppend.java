public class EventAppend implements Event {
    private int oldEnd;

    public EventAppend(int oldEnd) {
        this.oldEnd = oldEnd;
    }

    public EventType getEventType() {
        return EventType.APPEND;
    }

    public int getOldEnd() {
        return oldEnd;
    }
}
