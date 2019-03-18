package listeningrain.cn.blog.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Author: listeningrain
 * Date: 2019-03-18 21:59
 * Description: cookie的帮助类
 */
public class CookieUtils {

    private static final String LOGIN_COOKIE="loginCookie";

    /**
     * 构建发送给用户端的登录cookie
     * @param identityId
     * @return
     */
    public static Cookie getCookie(String identityId){
        Cookie cookie = new Cookie(LOGIN_COOKIE,identityId);
        cookie.setMaxAge(100000);
        cookie.setPath("/");
        return cookie;
    }

    //从客户端携带的cookie中找出登录的cookie
    public static String getLoginCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(null == cookies || 0 == cookies.length)
            return null;
        for(Cookie cookie : cookies){
            if(LOGIN_COOKIE.equals(cookie.getName())){
                String value = cookie.getValue();
                return value;
            }
        }
        return null;
    }
}
