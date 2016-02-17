package Realization;

import Interface.DependencyContainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AbstractModule implements DependencyContainer {
    private Map<Class, Class> listOfDependence;

    protected AbstractModule() throws ClassNotFoundException {
        listOfDependence = new HashMap<Class, Class>();

    }

    protected AbstractModule(String path) throws IOException, ClassNotFoundException {
        super();
        read(path);
    }

    public void addDependence(Class class1, Class class2) throws ClassNotFoundException {
        listOfDependence.put(class1, class2);
    }


    public Class getDependence(Class class1) {
        return listOfDependence.get(class1);
    }

    private void addDependence(String s) throws ClassNotFoundException {
        listOfDependence.put(Class.forName(s.substring(0, s.indexOf("="))),
                Class.forName(s.substring(s.indexOf("=") + 1)));
    }

    private void read(String fileName) throws ClassNotFoundException, IOException {
        exists(fileName);
        try (BufferedReader in = new BufferedReader(new FileReader(new File(fileName)))) {
            String s;
            while ((s = in.readLine()) != null) {
                addDependence(s);
            }
        }
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

}
