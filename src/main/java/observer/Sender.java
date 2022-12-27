package observer;

public interface Sender {
    void register(Member obj);
    void unregister(Member obj);

    void insert(int offset, String obj);
    void append(String obj);
    void delete(int start, int end);
    void undo();
}
