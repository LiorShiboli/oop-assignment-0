public class EventReverse implements Event {
    /**
     * constructs the event
     */
    public EventReverse() { }

    /**
     *
     * @return the event that has happened(reverse)
     */
    public EventType getEventType() {
        return EventType.REVERSE;
    }
}
