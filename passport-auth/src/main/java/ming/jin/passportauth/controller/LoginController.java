package ming.jin.passportauth.controller;

import ming.jin.passportauth.bean.User;
import ming.jin.passportauth.constant.AccountType;
import ming.jin.passportauth.mapper.UserMapper;
import ming.jin.passportauth.service.TokenServiceImpl;
import ming.jin.passportauth.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Haokun
 * @date 2020/2/26 22:14
 * <p>
 * LoginModule
 */

@Controller
@RequestMapping("user")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
//    token有效期10天
    private static final Long TOKEN_EXPIRE=60*60*24*10*1000L;


    @Autowired
    TokenServiceImpl tokenService;
    @Autowired
    UserMapper userMapper;

    /**
     * 用户注册
     * @param userName 账号
     * @param password 密码
     * @param accountType 手机还是邮箱注册
     * @return 返回是否成功
     */
    public boolean register(String userName, String password, int accountType) {
        User user = new User();
        boolean exist = existUserName(userName, accountType);
        boolean registerSuccess = false;
        if (exist) {
            log.info("用户名重复：" + userName);
        } else {
            String encodedPassword = MD5Util.md5(password);
            user.setPassword(encodedPassword);
            boolean setSuccess=setUserName(userName,accountType,user);
            int insertCount=0;
            if (setSuccess){
                insertCount=userMapper.addUser(user);
                String userId=user.getId();
                if (insertCount==1){
//                    产生token并存入数据库
                    String token=MD5Util.md5(password+userId).toLowerCase();
                    int count=tokenService.insertToken(userId,token,TOKEN_EXPIRE);
                    registerSuccess=true;
                }
            }

        }


        return registerSuccess;

    }

    @RequestMapping("login")
    @ResponseBody
    public String login(String userName, String password,int accountType) {
        User user = new User();
        String encodedPassword=MD5Util.md5(password).toLowerCase();
        String passwordInDB=null;
        switch (accountType) {
            default:
                log.info("accountType error");
                break;
            case AccountType.MOBILE:
                if (userName.length() == 11 && userName.charAt(0) == '1') {
                    user = userMapper.getUserByMobile(userName);
                }
                break;
            case AccountType.EMAIL:
                if (userName.contains("@")) {
                    user = userMapper.getUserByEmail(userName);
                }
                break;
        }
        passwordInDB=user.getPassword();
        String userId=user.getId();
        String token=null;
        if (encodedPassword.equals(passwordInDB)){
            token=tokenService.getToken(userId);

        }



        return token;
    }


    private boolean existUserName(String userName, int accountType) {
        int count = 0;
        switch (accountType) {
            default:
                log.info("accountType error");
                break;
            case AccountType.MOBILE:
                if (userName.length() == 11 && userName.charAt(0) == '1') {
                    count = userMapper.exist("mobile",userName);
                }
                break;
            case AccountType.EMAIL:
                if (userName.contains("@")) {
                    count = userMapper.exist("email",userName);
                }
                break;
        }

        return count > 0;

    }

    private boolean setUserName(String userName, int accountType, User user) {
        boolean success = false;
        switch (accountType) {
            default:
                log.info("accountType error");
                break;
            case AccountType.MOBILE:
                user.setMobile(userName);
                success=true;
                break;
            case AccountType.EMAIL:
                user.setEmail(userName);
                success=true;
                break;
        }


        return success;
    }

}
