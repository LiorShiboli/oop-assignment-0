package observer;

import java.util.ArrayList;

/**
 * Simple Sender based ArrayList
 */
public class GroupAdmin implements Sender {

    // Members
    /**
     * The UndoableStringBuilder
     */
    private final UndoableStringBuilder undoableStringBuilder;

    /**
     * List of members that in the group admin
     */
    private final ArrayList<Member> members;

    /**
     * Start with empty UndoableStringBuilder
     */
    public GroupAdmin() {
        this.undoableStringBuilder = new UndoableStringBuilder();
        this.members = new ArrayList<>();
    }

    /**
     * Start with UndoableStringBuilder with value
     * @param seq the value
     */
    public GroupAdmin(CharSequence seq) {
        this.undoableStringBuilder = new UndoableStringBuilder(seq);
        this.members = new ArrayList<>();
    }

    /**
     * Start with empty UndoableStringBuilder
     * Set the capacity of the UndoableStringBuilder
     * @param capacity the new capacity
     */
    public GroupAdmin(int capacity) {
        this.undoableStringBuilder = new UndoableStringBuilder(capacity);
        this.members = new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.undoableStringBuilder.toString();
    }

    // Getters
    public UndoableStringBuilder getUndoableStringBuilder() {
        return undoableStringBuilder;
    }

    // Registration actions
    @Override
    public void register(Member member) {
        this.members.add(member);
    }

    @Override
    public void unregister(Member member) {
        int i = this.members.indexOf(member);

        if (i >= 0) {
            this.members.set(i, this.members.get(this.members.size() - 1));
            this.members.remove(this.members.size() - 1);
        }
    }

    // UndoableStringBuilder actions
    @Override
    public void append(String str) {
        this.undoableStringBuilder.append(str);
        this.updateMembers();
    }

    @Override
    public void delete(int start, int end) {
        this.undoableStringBuilder.delete(start, end);
        this.updateMembers();
    }

    @Override
    public void insert(int offset, String str) {
        this.undoableStringBuilder.insert(offset, str);
        this.updateMembers();
    }

    @Override
    public void undo() {
        this.undoableStringBuilder.undo();
        this.updateMembers();
    }

    // Update

    /**
     * private methode for updates the members
     */
    private void updateMembers() {
        for (Member member : this.members) {
            member.update(this.undoableStringBuilder);
        }
    }
}
