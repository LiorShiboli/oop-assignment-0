class ReplaceEvent implements Event {
    private int start;
    private int end;
    private String replacedString;

    /**
     *
     * @param start          the index at which the start of the string that been replaced
     * @param end            the index that the word that replaced the string ended
     * @param replacedString the string that been replaced
     */
    public ReplaceEvent(int start, int end, String replacedString) {
        this.start = start;
        this.end = end;
        this.replacedString = replacedString;
    }

    /**
     * {@inheritDoc}
     */
    public EventType getEventType() {
        return EventType.REPLACE;
    }

    /**
     * @return the index at which the start of the string that been replaced
     */
    public int getStart() {
        return start;
    }

    /**
     * @return the index that the word that replaced the string ended
     */
    public int getEnd() {
        return end;
    }

    /**
     * @return the string that been replaced
     */
    public String getReplacedString() {
        return replacedString;
    }
}
