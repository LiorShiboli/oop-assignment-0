package observer;

import java.util.ArrayList;
import java.util.Stack;

public class GroupAdmin extends UndoableStringBuilder implements Sender {
    // Members
    private int membersLength = 0;
    private ArrayList<Member> members;

    public GroupAdmin() {
        super();
        this.members = new ArrayList<>();
    }

    public GroupAdmin(CharSequence seq) {
        super(seq);
        this.members = new ArrayList<>();
    }

    public GroupAdmin(int capacity) {
        super(capacity);
        this.members = new ArrayList<>();
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
        super.append(str);
        this.updateMembers();
    }

    @Override
    public void delete(int start, int end) {
        super.delete(start, end);
        this.updateMembers();
    }

    @Override
    public void insert(int offset, String str) {
        super.insert(offset, str);
        this.updateMembers();
    }

    @Override
    public void replace(int start, int end, String str) {
        super.replace(start, end, str);
        // this.updateMembers(); // not in Sender
    }

    @Override
    public void reverse() {
        super.reverse();
        // this.updateMembers(); // not in Sender
    }

    @Override
    public void undo() {
        super.undo();
        this.updateMembers();
    }

    // Update
    private void updateMembers() {
        for (int i = 0; i < this.membersLength; i++) {
            this.members.get(i).update(this);
        }
    }
}
