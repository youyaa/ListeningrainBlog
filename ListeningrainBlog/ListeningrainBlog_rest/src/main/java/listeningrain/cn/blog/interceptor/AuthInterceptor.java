package listeningrain.cn.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: listeningrain
 * Date: 2018/10/4
 * Time: 12:47
 * Description:
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Object admin = request.getSession().getAttribute("admin");
        if(null != admin){
            return true;
        }
        response.sendRedirect("/admin/tologin");
        return false;
    }
}
