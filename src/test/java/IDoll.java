import Interface.Closable;

public interface IDoll extends Closable {
    void go();
    int getSize();
    void setSize(int size);
}
