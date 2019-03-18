package listeningrain.cn.blog.interceptor;

import listeningrain.cn.blog.utils.CookieUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: listeningrain
 * Date: 2018/10/4
 * Time: 12:47
 * Description:校验用户是否登录
 */
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从request中获取登录的cookie
        String loginCookie = CookieUtils.getLoginCookie(request);
        if(null != loginCookie){
            Object admin = request.getSession().getAttribute(loginCookie);
            if(null != admin){
                return true;
            }
        }

        response.sendRedirect("/admin/tologin");
        return false;
    }
}
