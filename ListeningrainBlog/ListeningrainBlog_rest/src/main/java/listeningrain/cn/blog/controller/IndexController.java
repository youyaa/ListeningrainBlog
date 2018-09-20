package listeningrain.cn.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:08
 * Description:
 */
@Controller
public class IndexController {


    @GetMapping(path = "/index")
    public String index(){
        System.out.println("你好");
        //String name = indexService.getName();
        return "test";
    }
}
