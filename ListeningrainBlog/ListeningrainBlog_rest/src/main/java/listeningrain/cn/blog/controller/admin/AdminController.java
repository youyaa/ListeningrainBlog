package listeningrain.cn.blog.controller.admin;

import listeningrain.cn.blog.input.data.ContentsInputData;
import listeningrain.cn.blog.input.data.MetasInputData;
import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.ContentsOutputData;
import listeningrain.cn.blog.output.data.MetasOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.ContentsService;
import listeningrain.cn.blog.service.api.MetasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * author: listeningrain
 * Date: 2018/10/3
 * Time: 21:05
 * Description: 后台管理控制类
 */
@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private ContentsService contentsService;
    @Autowired
    private MetasService metasService;

    @RequestMapping()
    public String index(){
        return "redirect:/admin/index";
    }

    //后台首页
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String admin(){
        return "admin/index";
    }

    //去文章编辑页
    @RequestMapping(path = "/index/post", method = RequestMethod.GET)
    public String post(){
        return "admin/post";
    }

    //保存文章
    @RequestMapping(path = "/index/save", method = RequestMethod.POST)
    @ResponseBody
    public PojoInputDTO save(@RequestBody PojoInputDTO<ContentsInputData> pojoInputDTO){
        PojoOutputDTO pojoOutputDTO = contentsService.addContent(pojoInputDTO);
        return pojoInputDTO;
    }

    //文章列表页
    @RequestMapping(path = "/index/list", method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        PageInputDTO pageInputDTO = new PageInputDTO<>();
        pageInputDTO.setPageSize(10);
        PageOutputDTO<ContentsOutputData> contentsByPage = contentsService.getContentsByPage(pageInputDTO);
        modelMap.addAttribute("contents",contentsByPage);
        return "admin/list";
    }

    @RequestMapping(path = "/index/link",method = RequestMethod.GET)
    public String link(ModelMap modelMap){
        PojoInputDTO pojoInputDTO = new PojoInputDTO<>();
        MetasInputData metasInputData = new MetasInputData();
        metasInputData.setType("LINK");
        pojoInputDTO.setData(metasInputData);
        PageOutputDTO<MetasOutputData> allLinks = metasService.getMetasByType(pojoInputDTO);
        modelMap.addAttribute("links",allLinks);
        return "admin/links";
    }

    /**
     * 接收前端的ajax请求(根据友链的id获取内容)
     */
    @RequestMapping(path = "/index/editLink", method = RequestMethod.GET)
    @ResponseBody
    public PojoOutputDTO<MetasOutputData> editLink(@RequestParam Integer mid){
        PojoInputDTO<MetasInputData> pojoInputDTO = new PojoInputDTO<>();
        MetasInputData metasInputData = new MetasInputData();
        metasInputData.setMid(mid);
        pojoInputDTO.setData(metasInputData);
        PojoOutputDTO<MetasOutputData> metasById = metasService.getMetasById(pojoInputDTO);
        return  metasById;
    }

    @RequestMapping(path = "/index/updateLink", method = RequestMethod.POST)
    @ResponseBody
    public PojoOutputDTO updateLink(@RequestBody PojoInputDTO<MetasInputData> pojoInputDTO){
        PojoOutputDTO pojoOutputDTO = metasService.updateMetas(pojoInputDTO);
        return pojoOutputDTO;
    }
}
