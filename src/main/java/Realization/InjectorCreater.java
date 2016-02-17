package Realization;

import Interface.DependancyContainerCreator;
import Interface.DependencyContainer;
import Interface.ObjectContainer;
import Interface.ObjectContainerCreater;

import java.io.IOException;


public class InjectorCreater implements ObjectContainerCreater {

    private InjectorCreater() {
    }

    private static volatile InjectorCreater instance;

    public static InjectorCreater getInstance() {
        InjectorCreater localInstance = instance;
        if (localInstance == null) {
            synchronized (InjectorCreater.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new InjectorCreater();
                }
            }
        }
        return localInstance;
    }

    @Override
    public ObjectContainer create(DependencyContainer container) {
        return new Injector(container);
    }

    @Override
    public ObjectContainer create(DependancyContainerCreator creator, String path) throws IOException, ClassNotFoundException {
        return new Injector(creator.create(path));
    }

    @Override
    public ObjectContainer create(String path) throws IOException, ClassNotFoundException {
        return new Injector(AbstractModuleCreater.getInstance().create(path));
    }

    @Override
    public ObjectContainer create() throws ClassNotFoundException {
        return new Injector(AbstractModuleCreater.getInstance().create());
    }


}
