package team.union.plugin.spring.aop.validate;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import team.union.plugin.spring.aop.validate.annotations.Validate;
import team.union.tool.core.CommonUtils;

@Component("validateInterceptor")
public class ValidateInterceptor implements MethodInterceptor, Serializable, InitializingBean {

    private static final long serialVersionUID = -3725490886006496973L;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String[] groups = new String[] {};
        Object[] args = invocation.getArguments();
        String targetMethodName = invocation.getMethod().getName();
        Validate validate = invocation.getMethod().getAnnotation(Validate.class);
        if(CommonUtils.isNotEmpty(validate)) {
            groups = validate.group();
            for (Object objArg : args) {
                ValidatorBus.validatorAdapter(targetMethodName,groups,objArg);
            }
        }
        return invocation.proceed();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

}
