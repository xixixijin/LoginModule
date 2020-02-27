package ming.jin.passportauth.utils;

import org.springframework.util.DigestUtils;

/**
 * @author Haokun
 * @date 2020/2/26 23:06
 * <p>
 * LoginModule
 */
public class MD5Util {
    public static String md5(String message){
        return DigestUtils.md5DigestAsHex(message.getBytes());
    }
}
