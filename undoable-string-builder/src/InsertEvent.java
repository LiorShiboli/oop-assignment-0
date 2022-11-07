public class InsertEvent implements Event {
    private int start;
    private int length;

    /**
     *
     * @param start the index of inserted string
     * @param length the length of inserted string
     */
    public InsertEvent(int start, int length) {
        this.start = start;
        this.length = length;
    }

    /**
     * {@inheritDoc}
     */
    public EventType getEventType() {
        return EventType.INSERT;
    }

    /**
     * @return the index of inserted string
     */
    public int getStart() {
        return start;
    }

    /**
     * @return the length of inserted string
     */
    public int getLength() {
        return length;
    }
}
