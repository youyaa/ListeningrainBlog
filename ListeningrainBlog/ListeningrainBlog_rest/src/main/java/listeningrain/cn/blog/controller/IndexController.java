package listeningrain.cn.blog.controller;

import listeningrain.cn.blog.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:08
 * Description:
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping(path = "/index")
    public String index(){
        System.out.println("你好");
        String name = indexService.getName();
        return name;
    }
}
