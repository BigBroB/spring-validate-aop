package team.union.plugin.spring.aop.validate.validator;

import org.springframework.stereotype.Component;

import com.xiaoleilu.hutool.util.ReUtil;

import team.union.plugin.spring.aop.validate.AbsValidator;
import team.union.plugin.spring.aop.validate.annotations.RegxCommon;
import team.union.tool.exception.BaseException;
import team.union.tool.response.ResponseCode;

/** 
 * @ClassName: RegxCommonValidator 
 * @Description: 通用正则验证 
 * @author bigbro_B
 * @date 2017年11月24日 下午3:16:59 
 *  
 */
@Component("regxCommonValidator")
public class RegxCommonValidator extends AbsValidator<RegxCommon> {

    @Override
    public boolean validate(String[] groups, RegxCommon annotation, String fieldName, Object value)
            throws BaseException {
        String content = (String) value;
        if (!ReUtil.isMatch(annotation.regx(), content)) {
            throw new BaseException(ResponseCode.PARAMETER_ILLEGAL.getCode(),
                    String.format("%s ：%s参数不合法", fieldName, content));
        }
        return true;
    }

}
