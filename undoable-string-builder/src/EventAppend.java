package assignmentzero
public class EventAppend implements Event{
    final static EventType type = EventType.APPEND;
    private String appendedWord;
    public EventType getEventType();
        {
            return type;
        }

    public String getAppendedWord() {
        return appendedWord;
    }

    public void setAppendedWord(String appendedWord) {
        this.appendedWord = appendedWord;
    }
}
