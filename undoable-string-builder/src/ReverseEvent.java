public class ReverseEvent implements Event {
    /**
     *
     */
    public ReverseEvent() { }

    /**
     * {@inheritDoc}
     */
    public EventType getEventType() {
        return EventType.REVERSE;
    }
}
