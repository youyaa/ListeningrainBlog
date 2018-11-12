package listeningrain.cn.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/10/4
 * Time: 12:52
 * Description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        list.add("/admin/login");
        list.add("/admin/tologin");
       // registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/admin/**").excludePathPatterns(list);
    }

}
