package observer;

public class ConcreteMember implements Member {
    private UndoableStringBuilder usb;
    private final String name;

    private final int minLength;
    private final int maxLength;
    private final String addToEnd;

    public ConcreteMember(String name) {
        this.name = name;
        this.minLength = 0;
        this.maxLength = Integer.MAX_VALUE;
        this.addToEnd = null;
    }

    public ConcreteMember(String name, int minLength, String addToEnd) {
        this(name, minLength, Integer.MAX_VALUE, addToEnd);
    }

    public ConcreteMember(String name, int minLength, int maxLength, String addToEnd) {
        this.name = name;
        this.minLength = Math.max(minLength, 0);
        this.maxLength = Math.max(maxLength, minLength);

        if (this.minLength == 0) {
            this.addToEnd = null;
        } else {
            this.addToEnd = (addToEnd == null || addToEnd.length() == 0) ? "null" : addToEnd;
        }
    }

    // Getters
    public UndoableStringBuilder getUndoableStringBuilder() {
        return usb;
    }

    public UndoableStringBuilder getUsb() {
        return usb;
    }

    public String getName() {
        return name;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getAddToEnd() {
        return addToEnd;
    }

    // Update
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;

        System.out.println("INFO: Member " + name + " updated.");

        if (this.usb.toString().length() > this.maxLength){
            this.usb.delete(this.maxLength, this.usb.toString().length());
        } else {
            while (this.usb.toString().length() < this.minLength) {
                this.usb.append(this.addToEnd);
            }
        }
    }
}
