import Interface.Closable;


public class Boll implements Closable, IBoll {

    int size;

    public Boll() {
        this.size = 10;
    }

    public Boll(int size) {
        this.size = size;
    }

    @Override
    public void close() {

    }

    @Override
    public void kick() {

    }

    @Override
    public int getSize() {
        return size;
    }
}
