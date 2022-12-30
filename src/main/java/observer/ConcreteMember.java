package observer;

/**
 * Example for Member class
 * The member will print evey update a message.
 * If the minLength set than after update the UndoableStringBuilder
 * will be at last minLength chars
 * If the maxLength set than after update the UndoableStringBuilder
 * will be not more than maxLength chars
 */
public class ConcreteMember implements Member {
    /**
     * the UndoableStringBuilder of the Sender,
     * Note: This is not needed!
     */
    private UndoableStringBuilder usb;

    /**
     * The name of the ConcreteMember
     */
    private final String name;

    /**
     * Min length of the UndoableStringBuilder
     */
    private final int minLength;

    /**
     * Max length of the UndoableStringBuilder
     */
    private final int maxLength;

    /**
     * The string to add to the last of the UndoableStringBuilder
     * if it less than minLength
     */
    private final String addToEnd;

    private ConcreteMember() {
        this("");
    }

    /**
     * Simple ConcreteMember
     *
     * @param name the name of the ConcreteMember
     */
    public ConcreteMember(String name) {
        this.name = name;
        this.minLength = 0;
        this.maxLength = Integer.MAX_VALUE;
        this.addToEnd = null;
    }

    /**
     * ConcreteMember with minLength
     *
     * @param name the name of the ConcreteMember
     * @param minLength the min length
     * @param addToEnd The string to add to the last of the UndoableStringBuilder
     *                 if it less than minLength
     */
    public ConcreteMember(String name, int minLength, String addToEnd) {
        this(name, minLength, Integer.MAX_VALUE, addToEnd);
    }

    /**
     * ConcreteMember with minLength and maxLength
     *
     * @param name the name of the ConcreteMember
     * @param minLength the min length
     * @param maxLength the max length
     * @param addToEnd The string to add to the last of the UndoableStringBuilder
     *                 if it less than minLength
     */
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
