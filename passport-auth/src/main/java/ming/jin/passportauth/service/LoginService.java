package ming.jin.passportauth.service;

import ming.jin.passportauth.bean.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Haokun
 * @date 2020/2/26 16:32
 * <p>
 * LoginModule
 */
@Service
public class LoginService {
    @Autowired
    private TokenServiceImpl tokenService;


    public Passport getPassport(String userId){
        String token=tokenService.getToken(userId);
        Passport passport=new Passport();
        passport.setUserId(userId);
        passport.setToken(token);
        return passport;
    }

}
