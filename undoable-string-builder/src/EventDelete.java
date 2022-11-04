class EventDelete implements Event {
    private int start;
    private String deletedWord;

    public EventDelete(int start, String deletedWord) {
        this.start = start;
        this.deletedWord = deletedWord;
    }

    public EventType getEventType() {
        return EventType.DELETE;
    }
    public int getStart() {
        return this.start;
    }

    public String getDeletedWordWord() {
        return this.deletedWord;
    }
}
