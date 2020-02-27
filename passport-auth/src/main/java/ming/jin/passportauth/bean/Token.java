package ming.jin.passportauth.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Haokun
 */
public class Token implements Serializable {
//    用户id（主键）
    private String userId;
    private String token;
    private Date createTime;
    private Date updateTime;
//    存货时间 过期更换token
    private Long expire;

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
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
