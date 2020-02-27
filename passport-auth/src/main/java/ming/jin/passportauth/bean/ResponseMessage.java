package ming.jin.passportauth.bean;

/**
 * @author Haokun
 *
 * 响应信息
 */

public class ResponseMessage {

    private String userId;
    private String token;
    private String userName;

    /**
     * code 状态码
     * message 状态信息
     */
    private String code;
    private String message;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
