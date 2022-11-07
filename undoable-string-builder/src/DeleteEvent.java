class DeleteEvent implements Event {
    private int start;
    private String deletedString;

    /**
     *
     * @param start the index that the string has been deleted from
     * @param deletedString the string that been deleted
     */
    public DeleteEvent(int start, String deletedString) {
        this.start = start;
        this.deletedString = deletedString;
    }

    /**
     * {@inheritDoc}
     */
    public EventType getEventType() {
        return EventType.DELETE;
    }

    /**
     * @return the index that the string has been deleted from
     */
    public int getStart() {
        return this.start;
    }

    /**
     * @return the string that been deleted
     */
    public String getDeletedString() {
        return this.deletedString;
    }
}
