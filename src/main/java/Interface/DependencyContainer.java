package Interface;


public interface DependencyContainer {
    void addDependence(Class class1, Class class2) throws ClassNotFoundException;
    Class getDependence(Class class1);
}
