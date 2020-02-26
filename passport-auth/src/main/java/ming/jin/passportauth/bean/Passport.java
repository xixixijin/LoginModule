package ming.jin.passportauth.bean;

import java.io.Serializable;

/**
 * @author Haokun
 * @date 2020/2/26 16:24
 * <p>
 * LoginModule
 */
public class Passport implements Serializable {
    private static final long USER_ID_VERSION=1L;
    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
