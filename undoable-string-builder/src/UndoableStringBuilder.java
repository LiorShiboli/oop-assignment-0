import java.util.Stack;

public class UndoableStringBuilder {

    private StringBuilder builder;
    private Stack<Event> events;

    /**
     * Constructs a undoable string builder with no characters in it and an
     * initial capacity of 16 characters.
     */
    public UndoableStringBuilder() {
        this(new StringBuilder());
    }

    /**
     * Constructs a undoable string builder from origin string builder
     */
    private UndoableStringBuilder(StringBuilder builder) {
        this.builder = builder;
        this.events = new Stack<>();
    }

    /**
     * Constructs a undoable string builder with no characters in it and an
     * initial capacity specified by the {@code capacity} argument.
     *
     * @param      capacity  the initial capacity.
     * @throws     NegativeArraySizeException  if the {@code capacity}
     *               argument is less than {@code 0}.
     */
    public UndoableStringBuilder(int capacity) {
        this(new StringBuilder(capacity));
    }

    /**
     * Constructs a undoable string builder initialized to the contents of the
     * specified string. The initial capacity of the string builder is
     * {@code 16} plus the length of the string argument.
     *
     * @param   str   the initial contents of the buffer.
     */

    public UndoableStringBuilder(String str) {
        this(new StringBuilder(str));
    }

    /**
     * Constructs a undoable string builder that contains the same characters
     * as the specified {@code CharSequence}. The initial capacity of
     * the string builder is {@code 16} plus the length of the
     * {@code CharSequence} argument.
     *
     * @param      seq   the sequence to copy.
     */
    public UndoableStringBuilder(CharSequence seq) {
        this(new StringBuilder(seq));
    }

    public UndoableStringBuilder undo() {
        return this;
    }

    public UndoableStringBuilder append(String str) {
        return this;
    }

    public UndoableStringBuilder delete(int start, int end) {
        return this;
    }

    public UndoableStringBuilder insert(int offset, String str) {
        return this;
    }

    public UndoableStringBuilder replace(int start,int end, String str) {
        return this;
    }

    public UndoableStringBuilder reverse() {
        return this;
    }

    @Override
    public String toString() {
        return this.builder.toString();
    }
}
