package assignmentzero
class EventReplace implements Event{
    final static EventType type = EventType.REPLACE;
    private int start,end;
    private String replacedWord;
    public EventType getEventType();
    {
        return type;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getReplacedWord() {
        return replacedWord;
    }

    public void setReplacedWord(String replacedWord) {
        this.replacedWord = replacedWord;
    }
}
