package Interface;


import java.io.IOException;

public interface ObjectContainerCreater {

    ObjectContainer create(DependencyContainer container);

    ObjectContainer create(DependancyContainerCreator creater, String path) throws IOException, ClassNotFoundException;

    ObjectContainer create(String path) throws IOException, ClassNotFoundException;

    ObjectContainer create() throws IOException, ClassNotFoundException;
}
