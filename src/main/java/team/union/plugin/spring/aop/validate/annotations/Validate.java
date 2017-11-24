package team.union.plugin.spring.aop.validate.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** 
 * @ClassName: Validate 
 * @Description: aop切点 
 * @author bigbro_B
 * @date 2017年11月24日 下午2:03:23 
 *  
 */
@Target({ METHOD})
@Retention(RUNTIME)
@Documented
@Inherited
public @interface Validate {

    String[] group() default {};
    
}
