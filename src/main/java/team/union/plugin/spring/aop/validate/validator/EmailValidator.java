package team.union.plugin.spring.aop.validate.validator;

import org.springframework.stereotype.Component;

import com.xiaoleilu.hutool.util.ReUtil;

import team.union.plugin.spring.aop.validate.AbsValidator;
import team.union.plugin.spring.aop.validate.annotations.Email;
import team.union.tool.exception.BaseException;
import team.union.tool.response.ResponseCode;

/** 
 * @ClassName: EmailValidator 
 * @Description: 邮箱验证器 
 * @author bigbro_B
 * @date 2017年11月24日 下午2:02:51 
 *  
 */
@Component("emailValidator")
public class EmailValidator extends AbsValidator<Email> {

    private String emailRegx = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

    /** 
     * @Title: validate 
     * @Description: TODO
     * @param  groups validate的group参数
     * @param  annotation 自定义注解obj
     * @param  fieldName 参数名
     * @param  value 参数值
     * @return
     * @throws BaseException   
     */
    @Override
    public boolean validate(String[] groups, Email annotation, String fieldName, Object value) throws BaseException {
        String content = (String) value;
        if (!ReUtil.isMatch(emailRegx, content)) {
            throw new BaseException(ResponseCode.PARAMETER_ILLEGAL.getCode(),
                    String.format("%s %s不是正确的邮箱地址", fieldName, content));
        }
        return true;
    }

}
