package listeningrain.cn.blog.controller;

import listeningrain.cn.blog.input.data.ContentsInputData;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
    @Autowired
    private MetasService metasService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "redirect:/index/page/1";
    }

    //首页
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

    //文章详情
    @RequestMapping(path = "/index/post/{cid}",method = RequestMethod.GET)
    public String post(@PathVariable Integer cid, ModelMap modelMap ){
        PojoInputDTO<ContentsInputData> pojoInputDTO = new PojoInputDTO<>();
        ContentsInputData contentsInputData = new ContentsInputData();
        contentsInputData.setCid(cid);
        pojoInputDTO.setData(contentsInputData);
        PojoOutputDTO<ContentsOutputData> contentsById = contentsService.getContentsById(pojoInputDTO);
        modelMap.put("content",contentsById);
        return "post";
    }

    //友链
    @RequestMapping(path = "/index/link",method = RequestMethod.GET)
    public String link(ModelMap modelMap){
        PageOutputDTO<MetasOutputData> allLinks = metasService.getAllLinks(new PojoInputDTO<>());
        modelMap.addAttribute("links",allLinks);
        return "link";
    }

    //归档
    @RequestMapping(path = "/index/archive" ,method = RequestMethod.GET)
    public String archive(ModelMap modelMap){
        PageInputDTO pageInputDTO = new PageInputDTO();
        pageInputDTO.setPageSize(100);
        PageOutputDTO contentsByPage = contentsService.getContentsByPage(pageInputDTO);

        HashMap<String,List<ContentsOutputData>> map = new HashMap<>();
        Iterator iterator = contentsByPage.getData().iterator();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentsOutputData contentsOutputData = null;
        while (iterator.hasNext()) {
            contentsOutputData =(ContentsOutputData) iterator.next();
            Timestamp created = contentsOutputData.getCreated();
            String createdTime = sdf.format(created);
            if (!map.containsKey(createdTime)){
                List<ContentsOutputData> list = new ArrayList<>();
                list.add(contentsOutputData);
                map.put(createdTime, list);
            } else {
                List<ContentsOutputData> list = map.get(createdTime);
                list.add(contentsOutputData);
                map.put(createdTime,list);
            }
        }
        modelMap.addAttribute("archives",map);
        return "archive";
    }
}
