package observer;

public class ConcreteMember implements Member {
    private String name;

    private int minLength;
    private String addToEnd;

    public ConcreteMember(String name) {
        this.name = name;
        this.minLength = 0;
        this.addToEnd = null;
    }

    public ConcreteMember(String name, int minLength, String addToEnd) {
        this.name = name;
        this.minLength = Math.max(minLength, 0);

        if (this.minLength == 0) {
            this.addToEnd = null;
        } else {
            this.addToEnd = (addToEnd == null || addToEnd.length() == 0) ? "null" : addToEnd;
        }
    }


    @Override
    public void update(UndoableStringBuilder usb) {
        System.out.println("INFO: Member " + name + " updated.");

        int needed = usb.toString().length() - minLength;
        while (needed > 0) {
            usb.append(this.addToEnd);
            needed -= this.addToEnd.length();
        }
    }
}
