package listeningrain.cn.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * author: listeningrain
 * Date: 2018/10/3
 * Time: 21:05
 * Description: 后台管理控制类
 */
@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @RequestMapping()
    public String index(){
        return "redirect:/admin/index";
    }

    //后台首页
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String admin(){
        return "admin/index";
    }

    @RequestMapping(path = "/index/post", method = RequestMethod.GET)
    public String post(){
        return "admin/post";
    }
}
