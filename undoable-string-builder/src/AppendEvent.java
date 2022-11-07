public class AppendEvent implements Event {
    private int oldEnd;

    /**
     * @param oldEnd end of the string before the append event has happened
     */
    public AppendEvent(int oldEnd) {
        this.oldEnd = oldEnd;
    }

    /**
     * {@inheritDoc}
     */
    public EventType getEventType() {
        return EventType.APPEND;
    }

    /**
     * @return the end of the string before the append event has happened
     */
    public int getOldEnd() {
        return oldEnd;
    }
}
