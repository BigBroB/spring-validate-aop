package team.union.plugin.spring.aop.validate;

import java.lang.annotation.Annotation;

import team.union.tool.exception.BaseException;

/** 
 * @ClassName: AbsValidator 
 * @Description: 验证器抽象类 
 * @author bigbro_B
 * @date 2017年11月24日 下午2:02:36 
 * 
 * @param <T> 
 */
@SuppressWarnings("unchecked")
public abstract class AbsValidator<T extends Annotation> {

    public void validater(String[] groups,Annotation annotation ,String fieldName,Object value) throws BaseException{
        
        T targetAnnotation = (T)annotation;
        validate(groups,targetAnnotation,fieldName,value);
    }
    
    public abstract boolean validate(String[] groups,T annotation ,String fieldName,Object value) throws BaseException;
    
    
}
