package ming.jin.passportauth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Haokun
 * @date 2020/2/26 16:42
 * <p>
 * LoginModule
 */
@Service
public class RedisTokenService implements TokenService{
    private static final Logger log= LoggerFactory.getLogger(RedisTokenService.class);

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String getToken(String userId){
        Jedis jedis=jedisPool.getResource();
        String key="passport@"+userId;
        String token=jedis.get(key);
        jedis.close();
        log.info("RedisTokenService.getToken:key={},value={}",new Object[]{key,token});
        return token;
    }
    public void setToken(String userId,String token){
        Jedis jedis=jedisPool.getResource();
        String key="passport@"+userId;
        String result=jedis.set(key,token);
        jedis.close();
        log.info("RedisTokenService.setToken.result={}",result);
    }

    @Override
    public Integer insertToken(String userId, String token, Long expire) {
        this.setToken(userId,token);
        return null;
    }

    @Override
    public Integer updateToken(String userId, String token) {
        this.setToken(userId,token);
        return null;
    }
}
