import Annotation.ObjectPool;

@ObjectPool(maxSize = 4, corSize = 2)
public class Doll implements IDoll{
    int size;
    public Doll(int size){
        this.size=size;
    }
    public Doll(){
        this(6);
    }
    @Override
    public void close() {

    }

    @Override
    public void go() {

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size=size;
    }
}
