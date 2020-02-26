package ming.jin.passportauth.service;

/**
 * @author Haokun
 * @date 2020/2/26 16:53
 * <p>
 * LoginModule
 */
public interface TokenService {
    String getToken(String userId);
    Integer insertToken(String userId,String token,Long expire);
    Integer updateToken(String userId,String token);
}
