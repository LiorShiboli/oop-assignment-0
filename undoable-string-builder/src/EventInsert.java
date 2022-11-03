package assignmentzero
public class EventInsert implements Event {
        final static EventType type = EventType.INSERT;
        private int start, end;

        public EventType getEventType();

        {
                return type;
        }
}
