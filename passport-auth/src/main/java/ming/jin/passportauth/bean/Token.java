package ming.jin.passportauth.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Haokun
 * @date 2020/2/26 16:21
 * <p>
 * LoginModule
 */
public class Token implements Serializable {
    private String userId;
    private String token;
    private Date createTime;
    private Date updateTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
