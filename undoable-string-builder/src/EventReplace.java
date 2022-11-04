class EventReplace implements Event {
    private int start;
    private int end;
    private String replacedWord;

    public EventReplace(int start, int end, String replacedWord) {
        this.start = start;
        this.end = end;
        this.replacedWord = replacedWord;
    }

    public EventType getEventType() {
        return EventType.REPLACE;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getReplacedWord() {
        return replacedWord;
    }
}
