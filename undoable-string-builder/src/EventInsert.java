public class EventInsert implements Event {
    private int start;
    private int end;

    /**
     *
     * @param the indexes containing the inserted string
     */
    public EventInsert(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public EventType getEventType() {
        return EventType.INSERT;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
