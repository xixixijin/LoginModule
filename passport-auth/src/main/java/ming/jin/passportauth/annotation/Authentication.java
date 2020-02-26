package ming.jin.passportauth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Haokun
 * @date 2020/2/26 16:11
 * <p>
 * LoginModule
 *
 * 在需要认证的controller上使用这个注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authentication {
//   value为true则访问需要认证  为false则访问不需要认证 默认为true
    boolean value() default true;
}
