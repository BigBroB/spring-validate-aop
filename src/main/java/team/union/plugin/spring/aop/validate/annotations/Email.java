package team.union.plugin.spring.aop.validate.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** 
 * @ClassName: Email 
 * @Description: 邮箱验证注解 
 * @author bigbro_B
 * @date 2017年11月24日 下午2:03:12 
 *  
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Inherited
@BaseValidate(bean="emailValidator")
public @interface Email {

    String[] group() default {};
    
}
