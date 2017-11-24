package team.union.plugin.spring.aop.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.union.plugin.spring.aop.validate.annotations.BaseValidate;
import team.union.tool.core.CommonUtils;
import team.union.tool.core.ReflectHelper;
import team.union.tool.exception.BaseException;
import team.union.tool.response.ResponseCode;

/** 
 * @ClassName: ValidatorBus 
 * @Description: 通过spring bean 调用 验证器 
 * @author bigbro_B
 * @date 2017年11月24日 下午2:01:51 
 *  
 */
@SuppressWarnings("rawtypes")
public class ValidatorBus {
    private static Logger  logger = LoggerFactory.getLogger(ValidatorBus.class);
    
    @SuppressWarnings("unchecked")
    public static void validatorAdapter(String targetMethodName,String[] groups,Object obj) throws BaseException {
        try {
            Field[] fields = ReflectHelper.getFields(obj);
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    Annotation[] annotations = field.getAnnotations();
                    if (annotations != null && annotations.length > 0) {
                        Object fieldValue = ReflectHelper.getValueByFieldNameByGetMethod(obj, field.getName());
                        for(Annotation annotation :annotations) {
                            BaseValidate baseValidate = annotation.annotationType().getAnnotation(BaseValidate.class);
                            if(CommonUtils.isEmpty(baseValidate)) {
                                continue;
                            }
                            AbsValidator validator = ValidatorFactory.getValidator(baseValidate.bean());
                            validator.validate(groups,annotation,field.getName(),fieldValue);
                        }
                    }
                }
            }
        } catch (BaseException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new BaseException(e.getCode(),e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new BaseException(ResponseCode.PARAMETER_ILLEGAL.getCode(),e.getMessage());
        } 
    }

}
