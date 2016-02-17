package Annotation;

import java.lang.annotation.*;

@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
@Documented
public @interface ObjectPool {
    int maxSize() default 10;
    int corSize() default 5;

}
