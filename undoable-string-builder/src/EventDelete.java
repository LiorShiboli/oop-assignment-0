package assignmentzero
class EventDelete implements Event {
        final static EventType type = EventType.DELETE;
        private String deletedWord;
        private int Start;

        public EventType getEventType();

        {
                return type;
        }
        public int getStart() {
                return start;
        }

        public void setStart(int start) {
                this.start = start;
        }
        public String getDeletedWordWord() {
                return deletedWord;
        }

        public void setDeletedWordWord(String appendedWord) {
                this.deletedWord = deletedWord;
        }
}
