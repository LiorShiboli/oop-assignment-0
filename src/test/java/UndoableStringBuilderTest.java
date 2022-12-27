import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import observer.UndoableStringBuilder;
import org.junit.jupiter.api.*;

public class UndoableStringBuilderTest {
    @Test
    @DisplayName("build UndoableStringBuilder object")
    public void build() {
        UndoableStringBuilder builder1 = new UndoableStringBuilder();
        UndoableStringBuilder builder2 = new UndoableStringBuilder("with value");

        assertEquals("", builder1.toString());
        assertEquals("with value", builder2.toString());
    }

    @Test
    public void append() {
        UndoableStringBuilder builder = new UndoableStringBuilder();

        builder.append("The first line\n");
        assertEquals("The first line\n", builder.toString());

        builder.append("Another line\n");
        assertEquals("The first line\nAnother line\n", builder.toString());

        builder.undo();
        assertEquals("The first line\n", builder.toString());

        builder.append("A");
        builder.append("B");
        builder.undo();
        builder.undo();
        assertEquals("The first line\n", builder.toString());

        builder.append(null);
        assertEquals("The first line\nnull", builder.toString());

        builder.undo();
        assertEquals("The first line\n", builder.toString());
    }

    @Test
    public void delete() {
        UndoableStringBuilder builder = new UndoableStringBuilder("0123456789");

        builder.delete(0, 6);
        assertEquals("6789", builder.toString());

        builder.delete(1, 3);
        assertEquals("69", builder.toString());

        builder.undo();
        assertEquals("6789", builder.toString());

        builder.undo();
        assertEquals("0123456789", builder.toString());

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
        assertEquals("0123", builder.toString());

        builder.insert(4, "4567");
        assertEquals("01234567", builder.toString());

        builder.undo();
        assertEquals("0123", builder.toString());

        builder.insert(2, "---");
        assertEquals("01---23", builder.toString());

        builder.insert(2, null);
        assertEquals("01null---23", builder.toString());

        builder.undo();
        assertEquals("01---23", builder.toString());

        builder.undo();
        builder.undo();
        assertEquals("", builder.toString());

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.insert(-1, "");
        });

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.insert(1, "123");
        });
    }

    @Test
    public void replace() {
        UndoableStringBuilder builder = new UndoableStringBuilder("0123456789");

        builder.replace(0, 5, "----");
        assertEquals("----56789", builder.toString());

        builder.undo();
        assertEquals("0123456789", builder.toString());

        builder.replace(1, 6, "--");
        assertEquals("0--6789", builder.toString());

        builder.replace(1, 3, "AA");
        assertEquals("0AA6789", builder.toString());

        builder.undo();
        builder.undo();
        assertEquals("0123456789", builder.toString());

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.delete(-1, 1);
        });

        assertThrows(IndexOutOfBoundsException.class, ()-> {
            builder.delete(0, 30);
        });

        assertThrows(NullPointerException.class, ()-> {
           builder.replace(0, 1, null);
        });
    }

    @Test
    public void reverse() {
        UndoableStringBuilder builder = new UndoableStringBuilder("0123456789");

        builder.reverse();
        assertEquals("9876543210", builder.toString());

        builder.reverse();
        assertEquals("0123456789", builder.toString());

        builder.reverse();
        builder.reverse();
        assertEquals("0123456789", builder.toString());

        builder.undo();
        assertEquals("9876543210", builder.toString());

        builder.undo();
        assertEquals("0123456789", builder.toString());

        builder.undo();
        builder.undo();
        assertEquals("0123456789", builder.toString());
    }

    @Test
    @DisplayName("final test of the assignment")
    public void finalTest() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("to be or not to be");
        assertEquals(usb.toString(), "to be or not to be");

        usb.replace(3, 5, "eat");
        assertEquals(usb.toString(), "to eat or not to be");

        usb.replace(17, 19, "eat");
        assertEquals(usb.toString(), "to eat or not to eat");

        usb.reverse();
        assertEquals(usb.toString(), "tae ot ton ro tae ot");

        usb.undo();
        assertEquals(usb.toString(), "to eat or not to eat");

        usb.undo();
        assertEquals(usb.toString(), "to eat or not to be");

        usb.undo();
        assertEquals(usb.toString(), "to be or not to be");
    }
}
