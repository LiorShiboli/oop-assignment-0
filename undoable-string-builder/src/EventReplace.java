class EventReplace implements Event {
    private int start;
    private int end;
    private String replacedstring;

    /**
     *
     * @param start (the index at which the start of the string thats been replaced)
     * @param end (the index that the word that replaced the string ended
     * @param replacedstring (the string thats been replaced_
     */
    public EventReplace(int start, int end, String replacedstring) {
        this.start = start;
        this.end = end;
        this.replacedstring = replacedstring;
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

    public String getReplacedstring() {
        return replacedstring;
    }
}
