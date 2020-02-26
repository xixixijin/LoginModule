package ming.jin.passportauth.service;

import ming.jin.passportauth.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Haokun
 * @date 2020/2/26 16:51
 * <p>
 * LoginModule
 */
@Service
public class MysqlTokenService implements TokenService{

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public String getToken(String userId) {
        String token=tokenMapper.getToken(userId);
        return token;
    }

    @Override
    public Integer insertToken(String userId, String token, Long expire) {
        Integer count=tokenMapper.insert(userId,token,expire);
        return count;
    }

    @Override
    public Integer updateToken(String userId, String token) {
        Integer count=tokenMapper.updateToken(userId,token);

        return count;
    }
}
