package Interface;


import java.lang.reflect.InvocationTargetException;

public interface ObjectContainer {

    <T> T getInstance(Class<T> class1) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, HaventDependancyException, ClassHaventClosableInterfaceException, HaventFreeObjectException;
    <T> T getInstance(Class<T> class1, Class[] listOfParameters, Object[] paramets) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstanceleHasBeenCreatedException, HaventDependancyException;
    void addDependence(Class class1, Class class2) throws ClassNotFoundException;
}
