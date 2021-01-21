package cn.zlin.demo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义@Log注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy  .RUNTIME)
public @interface Log {
    String value() default "";
}
