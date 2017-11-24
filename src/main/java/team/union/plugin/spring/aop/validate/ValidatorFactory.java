package team.union.plugin.spring.aop.validate;

import team.union.tool.core.CommonUtils;
import team.union.tool.core.SpringBeanUtil;
import team.union.tool.exception.BaseException;
/** 
 * @ClassName: ValidatorFactory 
 * @Description: 获取验证器bean 
 * @author bigbro_B
 * @date 2017年11月24日 下午2:01:21 
 *  
 */
@SuppressWarnings("rawtypes")
public class ValidatorFactory {

    public static AbsValidator getValidator(String beanName) throws BaseException{
        AbsValidator validator = (AbsValidator)SpringBeanUtil.getBean(beanName);
        if(CommonUtils.isEmpty(validator)) {
            throw new BaseException("未找到对应的验证器");
        }
        return validator;
    }
    
    
    
    
    
    
}
