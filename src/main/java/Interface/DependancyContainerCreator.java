package Interface;


import java.io.IOException;

public interface DependancyContainerCreator {
    DependencyContainer create() throws IOException, ClassNotFoundException;
    DependencyContainer create(String path) throws IOException, ClassNotFoundException;
}
