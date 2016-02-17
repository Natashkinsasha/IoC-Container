package Realization;

import Interface.Closable;
import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;
import java.util.Map;


public class ClosableInvocationHandler implements MethodHandler {

    private Object obj;
    private Map<Object, Boolean> pool;

    public ClosableInvocationHandler(Object obj, Map<Object, Boolean> pool) {
        this.obj = obj;
        this.pool = pool;
    }

    @Override
    public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
        /*if (pool.get(obj)) {
            return null;
        }*/
        if (method.getName().equals("close")) {
            pool.put((Closable) obj, true);
        }
        return method.invoke(obj, objects);
    }
}
