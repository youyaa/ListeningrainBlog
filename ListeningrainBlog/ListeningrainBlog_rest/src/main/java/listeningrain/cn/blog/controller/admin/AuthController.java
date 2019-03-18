package listeningrain.cn.blog.controller.admin;

import listeningrain.cn.blog.input.data.AdminInputData;
import listeningrain.cn.blog.output.data.AdminOutputData;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.UsersService;
import listeningrain.cn.blog.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * author: listeningrain
 * Date: 2018/10/4
 * Time: 10:52
 * Description: 后台鉴权控制类
 */
@Controller
@RequestMapping(path = "/admin")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(path = "/tologin")
    public String tologin(ModelMap modelMap){
        return "admin/login";
    }

    @RequestMapping(path = "/login" ,method = RequestMethod.POST)
    public String login(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, AdminInputData adminInputData){
        /*AdminInputData adminInputData = new AdminInputData();
        adminInputData.setPassword(password);
        adminInputData.setUsername(username);*/
        PojoOutputDTO<AdminOutputData> admin = usersService.getAdmin(adminInputData);
        if(null != admin && null != admin.getData()){
            String identityId = UUID.randomUUID().toString();
            request.getSession().setAttribute(identityId,admin.getData());
            Cookie cookie = CookieUtils.getCookie(identityId);
            response.addCookie(cookie);
            return "redirect:/admin";
        }
        modelMap.addAttribute("error","账号或密码错误");
        return "/admin/login";
    }

    @RequestMapping(path = "/loginOut")
    public String loginOut(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "redirect:/admin/tologin";
    }
}
