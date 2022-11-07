import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;

public class UndoableStringBuilderTest {
    @Test
    @DisplayName("build UndoableStringBuilder object")
    public void build() {
        UndoableStringBuilder builder1 = new UndoableStringBuilder();
        UndoableStringBuilder builder2 = new UndoableStringBuilder("with value");

        assertEquals(builder1.toString(), "");
        assertEquals(builder2.toString(), "with value");
    }

    @Test
    public void append() {
        UndoableStringBuilder builder = new UndoableStringBuilder();

        builder.append("The first line\n");
        assertEquals(builder.toString(), "The first line\n");

        builder.append("Another line\n");
        assertEquals(builder.toString(), "The first line\nAnother line\n");

        builder.undo();
        assertEquals(builder.toString(), "The first line\n");

        builder.append("A");
        builder.append("B");
        builder.undo();
        assertEquals(builder.toString(), "The first line\nA");
    }

    @Test
    public void delete() {
        UndoableStringBuilder builder = new UndoableStringBuilder("0123456789");

        builder.delete(0, 6);
        assertEquals(builder.toString(), "6789");

        builder.delete(1, 3);
        assertEquals(builder.toString(), "69");

        builder.undo();
        assertEquals(builder.toString(), "6789");

        builder.undo();
        assertEquals(builder.toString(), "0123456789");

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.delete(-1, 2);
        });

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.delete(5, 1);
        });
    }

    @Test
    public void insert() {
        UndoableStringBuilder builder = new UndoableStringBuilder();

        builder.insert(0, "0123");
        assertEquals(builder.toString(), "0123");

        builder.insert(4, "4567");
        assertEquals(builder.toString(), "01234567");

        builder.undo();
        assertEquals(builder.toString(), "0123");

        builder.insert(2, "---");
        assertEquals(builder.toString(), "01---23");

        builder.undo();
        builder.undo();
        assertEquals(builder.toString(), "");

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.insert(-1, "");
        });

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.insert(1, "123");
        });

        assertThrows(NullPointerException.class, ()-> {
            builder.insert(0, null);
        });
    }

    @Test
    public void replace() {
        UndoableStringBuilder builder = new UndoableStringBuilder("0123456789");

        builder.replace(0, 5, "----");
        assertEquals(builder.toString(), "----56789");

        builder.undo();
        assertEquals(builder.toString(), "0123456789");

        builder.replace(1, 6, "--");
        assertEquals(builder.toString(), "0--6789");

        builder.replace(1, 3, "AA");
        assertEquals(builder.toString(), "0AA6789");

        builder.undo();
        builder.undo();
        assertEquals(builder.toString(), "0123456789");

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.delete(-1, 1);
        });

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.delete(0, 1);
        });
    }

    @Test
    public void reverse() {
        UndoableStringBuilder builder = new UndoableStringBuilder("0123456789");

        builder.reverse();
        assertEquals(builder.toString(), "9876543210");

        builder.reverse();
        assertEquals(builder.toString(), "0123456789");

        builder.reverse();
        builder.reverse();
        assertEquals(builder.toString(), "0123456789");

        builder.undo();
        assertEquals(builder.toString(), "9876543210");

        builder.undo();
        assertEquals(builder.toString(), "0123456789");

        builder.undo();
        builder.undo();
        assertEquals(builder.toString(), "0123456789");
    }
}
