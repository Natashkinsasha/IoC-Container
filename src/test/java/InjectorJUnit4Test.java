import Interface.*;
import Realization.InjectorCreater;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class InjectorJUnit4Test extends Assert {

    @Test
    public void testGetInstanceSingeltonWithoutException() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, HaventDependancyException, ClassHaventClosableInterfaceException, HaventFreeObjectException {
        ObjectContainer objectContainer = InjectorCreater.getInstance().create();
        objectContainer.addDependence(FabricaImpl.class, FabricaImpl.class);
        Fabrica fabrica = objectContainer.getInstance(FabricaImpl.class);
        fabrica.setName("fabrica");
        Fabrica fabrica2 = objectContainer.getInstance(FabricaImpl.class);
        fabrica2.setName("fabrica2");
        assertEquals(fabrica, fabrica2);
    }

    @Test
    public void testGetInstanceUsuallWithoutException() throws ClassNotFoundException, IllegalAccessException, HaventDependancyException, ClassHaventClosableInterfaceException, HaventFreeObjectException, InstantiationException, NoSuchMethodException, InvocationTargetException, InstanceleHasBeenCreatedException {
        ObjectContainer objectContainer = InjectorCreater.getInstance().create();
        objectContainer.addDependence(IBoll.class, Boll.class);
        IBoll boll1 = objectContainer.getInstance(IBoll.class);
        IBoll boll2 = objectContainer.getInstance(IBoll.class, new Class[]{int.class}, new Object[]{3});
        IBoll boll3 = objectContainer.getInstance(IBoll.class);
        assertNotEquals(boll3,boll1);
        assertEquals(boll2.getSize(), 3);
    }


    @Test
    public void testGetInstanceObjectPoolWithoutException() throws ClassNotFoundException, IllegalAccessException, HaventDependancyException, ClassHaventClosableInterfaceException, HaventFreeObjectException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ObjectContainer objectContainer = InjectorCreater.getInstance().create();
        objectContainer.addDependence(IDoll.class, Doll.class);
        IDoll doll1 = objectContainer.getInstance(IDoll.class);
        assertEquals(doll1.getSize(), 6);
        doll1.close();
        IDoll doll2 = objectContainer.getInstance(IDoll.class);
        doll2.close();
        IDoll doll3 = objectContainer.getInstance(IDoll.class);
        IDoll doll4 = objectContainer.getInstance(IDoll.class);
        doll3.close();
        IDoll doll5 = objectContainer.getInstance(IDoll.class);
        doll5.close();
        IDoll doll6 = objectContainer.getInstance(IDoll.class);
    }
}
