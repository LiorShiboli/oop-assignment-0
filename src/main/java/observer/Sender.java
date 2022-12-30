package observer;

/**
 * Interface that represent a UndoableStringBuilder Sender
 * Note: Changes only in the docs.
 */
public interface Sender {
    /**
     * Register member for updates
     * @param member the member
     */
    void register(Member member);

    /**
     * Unregister member from updates
     * @param member the member
     */
    void unregister(Member member);

    // actions
    void insert(int offset, String str);
    void append(String str);
    void delete(int start, int end);
    void undo();
}
