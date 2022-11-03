package assignmentzero
public class EventReverse implements Event {
        final static EventType type = EventType.REVERSE;

        public EventType getEventType();

        {
                return type;
        }
}
