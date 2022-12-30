package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {

    // Members
    private final UndoableStringBuilder undoableStringBuilder;
    private int membersLength = 0;

    private final ArrayList<Member> members;

    public GroupAdmin() {
        this.undoableStringBuilder = new UndoableStringBuilder();
        this.members = new ArrayList<>();
    }

    public GroupAdmin(CharSequence seq) {
        this.undoableStringBuilder = new UndoableStringBuilder(seq);
        this.members = new ArrayList<>();
    }

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
        if (this.members.size() == membersLength) {
            this.members.add(member);
        } else {
            this.members.set(membersLength, member);
        }
        membersLength++;
    }

    @Override
    public void unregister(Member member) {
        int i = this.members.indexOf(member);

        if (i >= 0) {
            this.members.set(i, this.members.get(membersLength - 1));
            this.members.set(membersLength - 1, null);
            membersLength--;
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
    private void updateMembers() {
        for (int i = 0; i < this.membersLength; i++) {
            this.members.get(i).update(this.undoableStringBuilder);
        }
    }
}
