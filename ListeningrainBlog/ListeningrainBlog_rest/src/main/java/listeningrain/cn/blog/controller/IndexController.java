package listeningrain.cn.blog.controller;

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
import listeningrain.cn.blog.utils.ThemeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:08
 * Description: 前端页面控制器
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
        ContentsInputData contentsInputData = new ContentsInputData();
        if(null != pageNum){
            pageInputDTO.setPageNum(pageNum);
        }
        contentsInputData.setStatus("publish");
        pageInputDTO.setData(contentsInputData);
        PageOutputDTO<ContentsOutputData> contentsByPage = contentsService.getContentsByPage(pageInputDTO);

        /**
         * 首页分页展示时，对文章内容进行截取，不需要显示完整的文章内容
         */
        for(ContentsOutputData contentsOutputData : contentsByPage.getData()){
            contentsOutputData.setContent(ThemeUtils.cutArticle(contentsOutputData.getContent()));
            /*if("md".equals(contentsOutputData.getType())){
                String content = ThemeUtils.articleTransfer(contentsOutputData.getContent());
                contentsOutputData.setContent(content);
            }*/
        }
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
        ContentsOutputData data = contentsById.getData();
        String content = ThemeUtils.articleTransfer(data.getContent());
        data.setContent(content);
        modelMap.put("content",contentsById);
        return "post";
    }

    //友链
    @RequestMapping(path = "/index/link",method = RequestMethod.GET)
    public String link(ModelMap modelMap){
        PageInputDTO<MetasInputData> pageInputDTO = new PageInputDTO<>();
        MetasInputData metasInputData = new MetasInputData();
        metasInputData.setType("LINK");
        pageInputDTO.setData(metasInputData);
        //友链默认查十条
        pageInputDTO.setPageSize(10);
        PageOutputDTO<MetasOutputData> allLinks = metasService.getMetasByType(pageInputDTO);
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
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentsOutputData contentsOutputData = null;
        String createdTime = null;
        while (iterator.hasNext()) {
            contentsOutputData =(ContentsOutputData) iterator.next();
            String created = contentsOutputData.getCreated();
            try {
                Date date = sdf.parse(created);
                createdTime = sdf.format(date);
            }catch (ParseException e){

            }

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

    //关于
    @RequestMapping(path = "/index/about", method = RequestMethod.GET)
    public String about(ModelMap modelMap){
        PageInputDTO<MetasInputData> pageInputDTO = new PageInputDTO<>();
        MetasInputData metasInputData = new MetasInputData();
        metasInputData.setType("ABOUT");
        pageInputDTO.setData(metasInputData);
        PageOutputDTO<MetasOutputData> pageOutputDTO = metasService.getMetasByType(pageInputDTO);
        if(null != pageOutputDTO){
            String content = ThemeUtils.articleTransfer(pageOutputDTO.getData().get(0).getContent());
            pageOutputDTO.getData().get(0).setContent(content);
        }
        modelMap.addAttribute("about",pageOutputDTO);
        return "about";
    }
}
