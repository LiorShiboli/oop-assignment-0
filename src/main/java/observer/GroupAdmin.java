package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {
    private UndoableStringBuilder usb;
    private int membersLength = 0;
    private ArrayList<Member> members;

    public GroupAdmin(UndoableStringBuilder usb) {
        this.members = new ArrayList<>();
        this.usb = usb;
    }

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
            this.members.set(i, this.members.get(membersLength));
            this.members.set(membersLength, null);
            membersLength--;
        }
    }

    // UndoableStringBuilder actions
    @Override
    public void append(String str) {
        this.usb.append(str);
        this.updateMembers();
    }

    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        this.updateMembers();
    }

    @Override
    public void insert(int offset, String str) {
        this.usb.insert(offset, str);
        this.updateMembers();
    }

    public void replace(int start, int end, String str)
    {
        this.usb.replace(start, end, str);
        this.updateMembers();
    }

    public void reverse() {
        this.usb.reverse();
        this.updateMembers();
    }

    @Override
    public void undo() {
        this.usb.undo();
        this.updateMembers();
    }

    // update
    private void updateMembers() {
        for (int i = 0; i < this.membersLength; i++) {
            this.members.get(i).update(usb);
        }
    }
}
