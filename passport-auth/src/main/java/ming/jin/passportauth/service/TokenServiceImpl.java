package ming.jin.passportauth.service;

import ming.jin.passportauth.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Haokun
 * @date 2020/2/26 16:40
 *
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private RedisTokenService redisTokenService;
    @Override
    public String getToken(String userId) {
        String token=redisTokenService.getToken(userId);
        if (StringUtils.isEmpty(token)){
            token=tokenMapper.getToken(userId);
        }
        return token;
    }

    @Override
    public Integer insertToken(String userId, String token, Long expire) {
        Integer count=tokenMapper.insert(userId,token,expire);
        if (count>0){
            redisTokenService.setToken(userId,token);
        }
        return count;
    }

    @Override
    public Integer updateToken(String userId, String token) {
        Integer count=tokenMapper.updateToken(userId,token);
        if (count>0){
            redisTokenService.setToken(userId,token);
        }
        return count;
    }
    public String copyTokenToRedis(String userId){
        String token=tokenMapper.getToken(userId);
        redisTokenService.setToken(userId,token);
        return token;
    }
}
