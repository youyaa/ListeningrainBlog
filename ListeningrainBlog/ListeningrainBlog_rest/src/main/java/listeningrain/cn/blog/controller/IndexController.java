package listeningrain.cn.blog.controller;

import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.output.data.ContentsOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.service.api.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:08
 * Description:
 */
@Controller
public class IndexController {

    @Autowired
    private ContentsService contentsService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "redirect:/index/page/1";
    }

    @RequestMapping(path = "/index/page/{pageNum}",method = RequestMethod.GET)
    public String index(ModelMap modelMap, @PathVariable Integer pageNum){
        PageInputDTO pageInputDTO = new PageInputDTO<>();
        if(null != pageNum){
            pageInputDTO.setPageNum(pageNum);
        }
        PageOutputDTO<ContentsOutputData> contentsByPage = contentsService.getContentsByPage(pageInputDTO);
        modelMap.addAttribute("contents",contentsByPage);
        return "index";
    }
}
