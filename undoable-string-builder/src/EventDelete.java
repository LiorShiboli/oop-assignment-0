class EventDelete implements Event {
    private int start;
    private String deletedstring;

    /**
     *
     * @param the index that the string has been deleted from
     * @param the string thats been deleted
     */
    public EventDelete(int start, String deletedstring) {
        this.start = start;
        this.deletedstring = deletedstring;
    }

    public EventType getEventType() {
        return EventType.DELETE;
    }
    public int getStart() {
        return this.start;
    }

    public String getDeletedWordWord() {
        return this.deletedstring;
    }
}
