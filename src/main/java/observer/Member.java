package observer;

/**
 * Interface that represent member of Sender
 * Note: Changes only in the docs.
 */
public interface Member {
    /**
     * Event when the sender of the member call an action
     * @param usb the UndoableStringBuilder of the Sender
     */
    void update(UndoableStringBuilder usb);
}
