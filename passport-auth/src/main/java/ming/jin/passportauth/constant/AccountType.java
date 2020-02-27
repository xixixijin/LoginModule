package ming.jin.passportauth.constant;


import org.springframework.stereotype.Component;

/**
 * @author Haokun
 * @date 2020/2/26 23:25
 * <p>
 * LoginModule
 */
@Component
public class AccountType{
    /**
     * mobile 手机号注册用户
     * email 邮箱注册用户
     */
    public static final int EMPTY=0;
    public static final int MOBILE=1;
    public static final int EMAIL=2;
    public static final int WECHAT=3;


}
