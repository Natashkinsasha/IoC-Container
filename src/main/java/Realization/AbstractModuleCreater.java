package Realization;


import Interface.DependancyContainerCreator;
import Interface.DependencyContainer;

import java.io.IOException;

public class AbstractModuleCreater implements DependancyContainerCreator {
    private static volatile AbstractModuleCreater instance;

    private AbstractModuleCreater() {
    }

    public static AbstractModuleCreater getInstance() {
        AbstractModuleCreater localInstance = instance;
        if (localInstance == null) {
            synchronized (AbstractModuleCreater.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AbstractModuleCreater();
                }
            }
        }
        return localInstance;
    }

    @Override
    public DependencyContainer create() throws ClassNotFoundException {
        return new AbstractModule();
    }

    @Override
    public DependencyContainer create(String path) throws IOException, ClassNotFoundException {
        return new AbstractModule(path);
    }
}
