import Annotation.Singleton;

@Singleton
public class FabricaImpl implements Fabrica {
    private String name;

    private FabricaImpl() {
        this.name = name;
    }

    private FabricaImpl(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FabricaImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
