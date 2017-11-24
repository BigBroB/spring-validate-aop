### 首先
项目集成了 hutool，并结合了网上的一些例子。    
作为新手，基于对 Spring Aop 的兴趣，产生了这个项目。  
首先 Spring 本身是有 validation 的，但是觉得用起来很麻烦，加之正在学习Aop，所以尝试着自己写了个Aop来实现 validation 的功能，适合新手去了解 AOP 以及 java 反射。  
这里是 Spring 的  [validation](http://jinnianshilongnian.iteye.com/blog/1990081)  

### 开始  
因为使用 Springboot 所以基于 Springboot  
目前设计的是，不符合参数要求直接抛出异常！


```
@ComponentScan(basePackages = {"team.union"})
```
首先在 ```Application.java``` 中添加 **注解扫描**  

一般都是在 *controller* 层进行参数验证

```
    @RequestMapping("index")
    @Validate(group={"1"})
    public Object test( TestVo vo) {
        return "ok";
    }
```
在方法上加上 ```@Validate``` 注解 , ```group```参数目的是为了 *VO* 层分组


```
public class TestVo {
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

```
再需要验证的地方加入 ```@Email``` 标签既可。
### 已实现注解

```
/**
 * 通用正则，regx为正则表达式
 */
@RegxCommon(regx = " ") 
/**
 * 邮箱
 */
@Email
```

### 扩展
目前，只写了关于邮箱参数的验证，和正则表达式通用验证，主要是因为懒。
之前设计的时候，考虑到扩展性。  
首先自定义注解
```
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Inherited
@BaseValidate(bean="emailValidator")
public @interface Email {
    String[] group() default {};
}
```
只要在自定义注解上加入 ```@BaseValidate``` 注解 ```bean``` 参数为验证器的*Spring bean*名



继承
```team.union.plugin.spring.aop.validate.AbsValidator```这个类，实现```validate```接口，并为该类 加上```@Component```注解，使其扫描为*bean*

这是邮箱验证的实现

```
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
```



