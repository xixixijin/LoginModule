package ming.jin.passportauth.constant;

import org.springframework.stereotype.Component;

/**
 * @author Haokun
 * @date 2020/2/27 20:41
 * <p>
 * LoginModule
 */
@Component
public class ResponseMsg {
    public static final String SUCCESS="0";
    public static final String SUCCESS_MESSAGE="成功";

    public static final String USER_ID_NULL="1";
    public static final String USER_ID_NULL_MSG="请输入用户名";

    public static final String TOKEN_IS_NULL="2";
    public static final String NULL_TOKEN_MSG="token为空请重新验证";

    public static final String TOKEN_FAILED="3";
    public static final String TOKEN_FAILED_MSG="token无效";

}
