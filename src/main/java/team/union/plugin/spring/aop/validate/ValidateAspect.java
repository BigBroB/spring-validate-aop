package team.union.plugin.spring.aop.validate;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import team.union.plugin.spring.aop.validate.annotations.Validate;
import team.union.tool.core.CommonUtils;

/** 
 * @ClassName: ValidateAspect 
 * @Description: aop 
 * @author bigbro_B
 * @date 2017年11月24日 下午2:02:26 
 *  
 */
@Aspect
@Component
public class ValidateAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("@annotation(team.union.plugin.spring.aop.validate.annotations.Validate)")
    public void validateAspect() {

    }

    @Before("validateAspect()")
    public void doBefore(JoinPoint joinPoint) throws Exception {

        Class<?> targetClass = joinPoint.getTarget().getClass();
        String targetMethodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Method[] methods = targetClass.getMethods();
        String[] groups = new String[] {};
        for (Method method : methods) {
            if (targetMethodName.equals(method.getName())) {
                Validate validate = method.getAnnotation(Validate.class);
                if (CommonUtils.isNotEmpty(validate)) {
                    groups = validate.group();
                }
            }
        }
        
        for (Object objArg : args) {
            ValidatorBus.validatorAdapter(targetMethodName,groups,objArg);
        }

    }

    
}
