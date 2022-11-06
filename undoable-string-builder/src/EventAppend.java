public class EventAppend implements Event {
    private int oldEnd;

    /**
     * constructs the event
     * @param oldEnd(end of the string before the append event has happened)
     */
    public EventAppend(int oldEnd) {
        this.oldEnd = oldEnd;
    }

    /**
     *
     * @return the event that has happened(append)
     */
    public EventType getEventType() {
        return EventType.APPEND;
    }

    /**
     * @return the end of the string before the append has happened
     */
    public int getOldEnd() {
        return oldEnd;
    }
}
