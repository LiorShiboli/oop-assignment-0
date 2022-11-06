import static org.junit.jupiter.api.Assertions.assertEquals;

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

    }

    @Test
    public void insert() {

    }

    @Test
    public void replace() {

    }

    @Test
    public void reverse() {

    }
}
