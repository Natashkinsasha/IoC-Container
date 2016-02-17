package Realization;

import Annotation.ObjectPool;
import Annotation.Singleton;
import Interface.*;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Injector implements Interface.ObjectContainer {
    private DependencyContainer dependencyContainer;
    private Map<Class, Map<Object, Boolean>> mapOfPools;
    private List<Object> listOfSingeltonObject;

    protected Injector(DependencyContainer dependancyContainer) {
        this.dependencyContainer = dependancyContainer;
        this.listOfSingeltonObject = new ArrayList<Object>();
        this.mapOfPools = new HashMap<>();
    }

    public <T> T getInstance(Class<T> class1) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, HaventDependancyException, ClassHaventClosableInterfaceException, HaventFreeObjectException {
        Class<T> class2 = dependencyContainer.getDependence(class1);
        if (class2 == null) {
            throw new HaventDependancyException();
        } else {
            if (class2.isAnnotationPresent(Singleton.class)) {
                return getSingeltonInstance(class2);
            }
            if (class2.isAnnotationPresent(ObjectPool.class)) {
                if (Closable.class.isAssignableFrom(class1)) {
                    return getPoolObjectInstance(class1);
                } else {
                    throw new ClassHaventClosableInterfaceException();
                }
            } else {
                return makeInstance(class2);
            }
        }
    }

    private <T> T getPoolObjectInstance(Class<T> class1) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, HaventFreeObjectException {
        Class<T> class2 = dependencyContainer.getDependence(class1);
        Map<Object, Boolean> pool = mapOfPools.get(class2);
        int maxPoolSize = class2.getAnnotation(ObjectPool.class).maxSize();
        int corPoolSize = class2.getAnnotation(ObjectPool.class).corSize();
        T object;
        if (pool == null) {
            pool = new HashMap<Object, Boolean>(maxPoolSize);
            for (int i = 0; i < corPoolSize; i++) {
                Closable a = makeInstance(class2);
                pool.put((Closable) a, new Boolean(true));
            }
            mapOfPools.put(class2, pool);
        }

        for (Map.Entry<Object, Boolean> entry : pool.entrySet()) {
            if ((entry.getValue()) && (entry.getKey() != null)) {
                entry.setValue(false);
                object = (T) entry.getKey();
                ProxyFactory factory = new ProxyFactory();
                factory.setSuperclass(class2);
                Object proxyObject = factory.create(new Class<?>[0], new Object[0], new ClosableInvocationHandler(object, pool));
                return (T) proxyObject;
            }
        }
        if (maxPoolSize > pool.size()) {
            Map<Closable, Boolean> newObject = new HashMap<>();
            Closable a = makeInstance(class2);
            pool.put((Closable) a, new Boolean(false));
            ProxyFactory factory = new ProxyFactory();
            factory.setSuperclass(class2);
            Object proxyObject = factory.create(new Class<?>[0], new Object[0], new ClosableInvocationHandler(a, pool));
            return (T) proxyObject;
        } else {
            throw new HaventFreeObjectException();
        }

    }

    public <T> T getInstance(Class<T> class1, Class[] listOfParameters, Object[] paramets)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstanceleHasBeenCreatedException, HaventDependancyException {
        Class<T> class2 = dependencyContainer.getDependence(class1);
        if (class2 == null) {
            throw new HaventDependancyException();
        } else {
            if (class2.isAnnotationPresent(Singleton.class)) {
                return getSingeltonInstance(class2, listOfParameters, paramets);
            } else {
                return (T) makeInstance(class2, listOfParameters, paramets);
            }
        }
    }

    @Override
    public void addDependence(Class class1, Class class2) throws ClassNotFoundException {
        dependencyContainer.addDependence(class1, class2);
    }

    synchronized private <T> T getSingeltonInstance(Class<T> class2) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        for (Object obj : listOfSingeltonObject) {
            if (class2.isAssignableFrom(obj.getClass())) {
                return (T) obj;
            }
        }
        T a = makeInstance(class2);
        listOfSingeltonObject.add(a);
        return a;
    }

    synchronized private <T> T getSingeltonInstance(Class<T> class2, Class[] listOfParameters, Object[] paramets)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstanceleHasBeenCreatedException {
        for (Object obj : listOfSingeltonObject) {
            if (class2.isAssignableFrom(obj.getClass())) {
                throw new InstanceleHasBeenCreatedException();
            }
        }
        T a = makeInstance(class2, listOfParameters, paramets);
        listOfSingeltonObject.add(a);
        return a;
    }

    private <T> T makeInstance(Class class2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = class2.getDeclaredConstructor();
        constructor.setAccessible(true);
        T a = (T) constructor.newInstance();
        return a;
    }

    private <T> T makeInstance(Class class2, Class[] listOfParameters, Object[] paramets) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = class2.getConstructor(listOfParameters);
        constructor.setAccessible(true);
        T a = (T) constructor.newInstance(paramets);
        return a;
    }


}
