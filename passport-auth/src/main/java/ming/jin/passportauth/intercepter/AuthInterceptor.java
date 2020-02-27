package ming.jin.passportauth.intercepter;
import com.alibaba.fastjson.JSON;
import ming.jin.passportauth.annotation.Authentication;
import ming.jin.passportauth.bean.Passport;
import ming.jin.passportauth.bean.ResponseMessage;
import ming.jin.passportauth.constant.ResponseMsg;
import ming.jin.passportauth.service.LoginService;
import ming.jin.passportauth.service.RedisTokenService;
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
import java.io.IOException;
import java.io.PrintWriter;

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
    private RedisTokenService redisTokenService;
    @Autowired
    private TokenServiceImpl tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod) {

            HandlerMethod handlerMethod = (HandlerMethod) handler;

//            判断controller是否有authentication注解
            boolean present = handlerMethod.getMethodAnnotation(Authentication.class).value();
            if (!present) {
                return true;
            }
            String userId = request.getHeader("userId");
            String token = request.getHeader("token");

            if (StringUtils.isEmpty(userId)) {
                responseError(response,ResponseMsg.USER_ID_NULL,ResponseMsg.USER_ID_NULL_MSG);
                return false;
            } else {
log.info("正在访问需要登陆的url");
                if (StringUtils.isEmpty(token)) {
                    responseError(response,ResponseMsg.TOKEN_IS_NULL,ResponseMsg.NULL_TOKEN_MSG);
                    return false;
                }
                String tokenInDB = tokenService.getToken(userId);
                if (StringUtils.isEmpty(tokenInDB)) {
                    responseError(response,ResponseMsg.TOKEN_FAILED,ResponseMsg.TOKEN_FAILED_MSG);
                    return false;
                } else if (!tokenInDB.equals(token)) {
                    responseError(response,ResponseMsg.TOKEN_FAILED,ResponseMsg.TOKEN_FAILED_MSG);
                    return false;
                }

            }

        }

        return true;
    }

//    响应错误信息
    private void responseError(HttpServletResponse response,String code,String message) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer= response.getWriter();

        ResponseMessage responseMessage=new ResponseMessage();
        responseMessage.setCode(code);
        responseMessage.setMessage(message);
        String json= JSON.toJSONString(responseMessage);
        writer.append(json);
        writer.flush();
        writer.close();
    }
}
