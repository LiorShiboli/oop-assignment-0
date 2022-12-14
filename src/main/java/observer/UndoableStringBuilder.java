package observer;

import java.awt.desktop.AppEvent;
import java.util.Stack;

/**
 * {@code  UndoableStringBuilder} using instance of {@link StringBuilder}
 * A mutable sequence of characters.  This class provides an API compatible
 * with {@code StringBuffer}, but with no guarantee of synchronization.
 * This class is designed for use as a drop-in replacement for
 * {@code StringBuffer} in places where the string buffer was being
 * used by a single thread (as is generally the case).   Where possible,
 * it is recommended that this class be used in preference to
 * {@code StringBuffer} as it will be faster under most implementations.
 *
 * <p>The principal operations on a {@code UndoableStringBuilder} are the
 * {@code append} and {@code insert} methods, which are
 * overloaded so as to accept data of any type. Each effectively
 * converts a given datum to a string and then appends or inserts the
 * characters of that string to the {@code UndoableStringBuilder}. The
 * {@code append} method always adds these characters at the end
 * of the builder; the {@code insert} method adds the characters at
 * a specified point.
 * <p>
 * For example, if {@code z} refers to a {@code UndoableStringBuilder} object
 * whose current contents are "{@code start}", then
 * the method call {@code z.append("le")} would cause the {@code UndoableStringBuilder}
 * to contain "{@code startle}", whereas
 * {@code z.insert(4, "le")} would alter the {@code UndoableStringBuilder} to
 * contain "{@code starlet}".
 * <p>
 * In general, if sb refers to an instance of a {@code UndoableStringBuilder},
 * then {@code sb.append(x)} has the same effect as
 * {@code sb.insert(sb.length(), x)}.
 * <p>
 * Every undoable {@code UndoableStringBuilder} has a capacity. As long as the length of the
 * character sequence contained in the {@code UndoableStringBuilder} does not exceed
 * the capacity, it is not necessary to allocate a new internal
 * buffer. If the internal buffer overflows, it is automatically made larger.
 *
 * <p>Instances of {@code UndoableStringBuilder} are not safe for
 * use by multiple threads. If such synchronization is required then it is
 * recommended that {@link java.lang.StringBuffer} be used.
 *
 * <p>Unless otherwise noted, passing a {@code null} argument to a constructor
 * or method in this class will cause a {@link NullPointerException} to be
 * thrown.
 * @apiNote
 * This docs of {@code  UndoableStringBuilder} is based on the docs of {@link StringBuilder}
 */

public class UndoableStringBuilder {

    /**
     * The original StringBuilder
     */
    private StringBuilder builder;

    /**
     * Stack of the events that the instance dose
     */
    private Stack<Event> events;

    /**
     * Constructs a undoable string builder with no characters in it and an
     * initial capacity of 16 characters.
     */
    public UndoableStringBuilder() {
        this(new StringBuilder());
    }

    /**
     * Constructs a undoable string builder from string builder
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

    @Override
    public String toString() {
        return this.builder.toString();
    }

    /**
     * Appends the specified string to this character sequence.
     * <p>
     * The characters of the {@code String} argument are appended, in
     * order, increasing the length of this sequence by the length of the
     * argument. If {@code str} is {@code null}, then the four
     * characters {@code "null"} are appended.
     * <p>
     * Let <i>n</i> be the length of this character sequence just prior to
     * execution of the {@code append} method. Then the character at
     * index <i>k</i> in the new character sequence is equal to the character
     * at index <i>k</i> in the old character sequence, if <i>k</i> is less
     * than <i>n</i>; otherwise, it is equal to the character at index
     * <i>k-n</i> in the argument {@code str}.
     *
     * @param   str   a string.
     * @return  a reference to this object.
     */
    public void append(String str) {
        Event event = new AppendEvent(builder.toString().length() - 1);

        builder.append(str);

        events.push(event);
    }

    /**
     * Removes the characters in a substring of this sequence.
     * The substring begins at the specified {@code start} and extends to
     * the character at index {@code end - 1} or to the end of the
     * sequence if no such character exists. If
     * {@code start} is equal to {@code end}, no changes are made.
     *
     * @param      start  The beginning index, inclusive.
     * @param      end    The ending index, exclusive.
     * @return     This object.
     * @throws     StringIndexOutOfBoundsException  if {@code start}
     *             is negative, greater than {@code length()}, or
     *             greater than {@code end}.
     */
    public void delete(int start, int end) {
        // The function do not use try-catch. because, it is not needed

        String oldValue = builder.toString();

        Event event;

        if (end > oldValue.length()) {
            end = oldValue.length();
        }

        if (start < 0 || oldValue.length() < start || end < start) {
            throw new IndexOutOfBoundsException("start is out of bound");
        }

        String deletedStr = oldValue.substring(start, end);
        event = new DeleteEvent(start, deletedStr);

        builder.delete(start, end);

        events.push(event);
    }

    /**
     * Inserts the string into this character sequence.
     * <p>
     * The characters of the {@code String} argument are inserted, in
     * order, into this sequence at the indicated offset, moving up any
     * characters originally above that position and increasing the length
     * of this sequence by the length of the argument. If
     * {@code str} is {@code null}, then the four characters
     * {@code "null"} are inserted into this sequence.
     * <p>
     * The character at index <i>k</i> in the new character sequence is
     * equal to:
     * <ul>
     * <li>the character at index <i>k</i> in the old character sequence, if
     * <i>k</i> is less than {@code offset}
     * <li>the character at index <i>k</i>{@code -offset} in the
     * argument {@code str}, if <i>k</i> is not less than
     * {@code offset} but is less than {@code offset+str.length()}
     * <li>the character at index <i>k</i>{@code -str.length()} in the
     * old character sequence, if <i>k</i> is not less than
     * {@code offset+str.length()}
     * </ul><p>
     * The {@code offset} argument must be greater than or equal to
     * {@code 0}, and less than or equal to the length
     * of this sequence.
     *
     * @param      offset   the offset.
     * @param      str      a string.
     * @return     a reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invalid.
     */
    public void insert(int offset, String str) {
        // The function do not use try-catch. because, it is not needed

        String oldValue = builder.toString();

        if (offset < 0 || oldValue.length() < offset) {
            throw new IndexOutOfBoundsException("offset is out of bound");
        }

        Event event;
        if (str == null) {
            event = new InsertEvent(offset, 4); // null
        } else {
            event = new InsertEvent(offset, str.length());
        }

        builder.insert(offset, str);

        events.push(event);
    }

    /**
     * Replaces the characters in a substring of this sequence
     * with characters in the specified {@code String}. The substring
     * begins at the specified {@code start} and extends to the character
     * at index {@code end - 1} or to the end of the
     * sequence if no such character exists. First the
     * characters in the substring are removed and then the specified
     * {@code String} is inserted at {@code start}. (This
     * sequence will be lengthened to accommodate the
     * specified String if necessary.)
     *
     * @param      start    The beginning index, inclusive.
     * @param      end      The ending index, exclusive.
     * @param      str   String that will replace previous contents.
     * @return     This object.
     * @throws     StringIndexOutOfBoundsException  if {@code start}
     *             is negative, greater than {@code length()}, or
     *             greater than {@code end}.
     */
    public void replace(int start, int end, String str) {
        // The function do not use try-catch. because, it is not needed

        String oldValue = builder.toString();

        if (end > oldValue.length()) {
            end = oldValue.length();
        }

        if (start < 0 || oldValue.length() < start || end < start) {
            throw new IndexOutOfBoundsException("start is out of bound");
        }

        if (str == null) {
            throw new NullPointerException("str cannot be null");
        }

        String replacedString = oldValue.substring(start, end);
        Event event = new ReplaceEvent(start, start + str.length(), replacedString);

        builder.replace(start, end, str);

        events.push(event);
    }

    /**
     * Causes this character sequence to be replaced by the reverse of
     * the sequence. If there are any surrogate pairs included in the
     * sequence, these are treated as single characters for the
     * reverse operation. Thus, the order of the high-low surrogates
     * is never reversed.
     *
     * Let <i>n</i> be the character length of this character sequence
     * (not the length in {@code char} values) just prior to
     * execution of the {@code reverse} method. Then the
     * character at index <i>k</i> in the new character sequence is
     * equal to the character at index <i>n-k-1</i> in the old
     * character sequence.
     *
     * <p>Note that the reverse operation may result in producing
     * surrogate pairs that were unpaired low-surrogates and
     * high-surrogates before the operation. For example, reversing
     * "\u005CuDC00\u005CuD800" produces "\u005CuD800\u005CuDC00" which is
     * a valid surrogate pair.
     *
     * @return  a reference to this object.
     */
    public void reverse() {
        builder.reverse();

        events.push(new ReverseEvent());
    }

    /**
     * undo the last action the undoable string builder dos
     *
     * @return  a reference to this object.
     */
    public void undo() {
        // The function do not use try-catch. because, it is not needed

        // remove last event from events
        Event event = events.pop();

        // undo the last event
        switch (event.getEventType()) {
            case APPEND:
                AppendEvent eventA = (AppendEvent)event;
                builder.delete(eventA.getOldEnd() + 1, builder.length());
                break;
            case INSERT:
                InsertEvent eventI = (InsertEvent)event;
                builder.delete(eventI.getStart(), eventI.getStart() + eventI.getLength());
                break;
            case REPLACE:
                ReplaceEvent eventR = (ReplaceEvent)event;
                builder.replace(eventR.getStart(), eventR.getEnd(), eventR.getReplacedString());
                break;
            case DELETE:
                DeleteEvent eventD = (DeleteEvent)event;
                builder.insert(eventD.getStart(), eventD.getDeletedString());
                break;
            case REVERSE:
                builder.reverse();
                break;
        }
    }
}
