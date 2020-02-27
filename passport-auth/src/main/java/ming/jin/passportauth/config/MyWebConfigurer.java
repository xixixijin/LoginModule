package ming.jin.passportauth.config;
import ming.jin.passportauth.intercepter.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Haokun
 */
public class MyWebConfigurer implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//      addInterceptor 添加拦截器
//      addPathPatterns 添加拦截规则，
//        excludePathPatterns 排除拦截规则
        registry.addInterceptor(authInterceptor).addPathPatterns("/");
    }
}
