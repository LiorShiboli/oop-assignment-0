package assignmentzero
public class UndoableStringBuilder {
    public UndoableStringBuilder() {

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
        // TODO
        return "TODO";
    }
}
