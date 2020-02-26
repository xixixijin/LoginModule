package ming.jin.passportauth.intercepter;
import ming.jin.passportauth.annotation.Authentication;
import ming.jin.passportauth.bean.Passport;
import ming.jin.passportauth.service.LoginService;
import ming.jin.passportauth.service.TokenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Haokun
 * @date 2020/2/26 16:29
 * <p>
 * LoginModule
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log= LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenServiceImpl tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)){

            HandlerMethod handlerMethod=(HandlerMethod)handler;
            Object controller =handlerMethod.getBean();

            boolean present=controller.getClass().isAnnotationPresent(Authentication.class);
            if (!present){
                return true;
            }
            String userId=request.getHeader("userId");
            String token=request.getHeader("token");

            if (StringUtils.isEmpty(userId)){

                return false;
            }else {

                if (StringUtils.isEmpty(token)){

                    return false;
                }
                Passport passport=loginService.getPassport(userId);
                String tokenInDB=passport.getToken();
                if (StringUtils.isEmpty(tokenInDB)){

                    return false;
                }else if(!tokenInDB.equals(token)){

                    return false;
                }

            }

        }

        return true;
    }
}
