package listeningrain.cn.blog.controller;

import listeningrain.cn.blog.input.data.CommentsInputData;
import listeningrain.cn.blog.input.data.ContentsInputData;
import listeningrain.cn.blog.input.data.MetasInputData;
import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.ReturnMessage;
import listeningrain.cn.blog.output.data.CommentsOutputData;
import listeningrain.cn.blog.output.data.ContentsOutputData;
import listeningrain.cn.blog.output.data.MetasOutputData;
import listeningrain.cn.blog.output.data.UserShowInformationOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.CommentsService;
import listeningrain.cn.blog.service.api.ContentsService;
import listeningrain.cn.blog.service.api.MetasService;
import listeningrain.cn.blog.service.api.UserShowInformationService;
import listeningrain.cn.blog.utils.CacheUtils;
import listeningrain.cn.blog.utils.ThemeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
        pageInputDTO.setPageSize(4);
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
            //如果是第一次分页查询，将文章数据放入缓存中
            if(1 == pageInputDTO.getPageNum()){
                CacheUtils.put(contentsOutputData.getCid(),contentsOutputData);
            }
        }
        modelMap.addAttribute("contents",contentsByPage);


        //封装友链的数据
        link(modelMap);
        latestArticle(modelMap);
        userShowInformation(modelMap);
        getMotto(modelMap);
        return "index";
    }

    //封装近期文章数据
    private void latestArticle(ModelMap modelMap){
        List<ContentsOutputData> list = CacheUtils.toList();
        PageOutputDTO<ContentsOutputData> contentsByPage = new PageOutputDTO<>();
        contentsByPage.setData(list);
        modelMap.put("latest_contents",contentsByPage);
    }

    @Autowired
    private UserShowInformationService userShowInformationService;

    //获取用户的静态信息
    private void userShowInformation(ModelMap modelMap){
        if(null == CacheUtils.getUserShowInformationOutputData()){
            PojoOutputDTO<UserShowInformationOutputData> userShowInformation = userShowInformationService.getUserShowInformation();
            if(null != userShowInformation){
                if(null != CacheUtils.getUserShowInformationOutputData()){
                    CacheUtils.setUserShowInformationOutputData(userShowInformation.getData());
                }
            }
            CacheUtils.setUserShowInformationOutputData(userShowInformation.getData());
            modelMap.put("userShow",CacheUtils.getUserShowInformationOutputData());
        }else{
            System.out.println("----------从缓存中取-----------");
            modelMap.put("userShow",CacheUtils.getUserShowInformationOutputData());
        }
    }

    //获取数据库的名言并缓存起来
    public void getMotto(ModelMap modelMap){
        MetasOutputData motto = CacheUtils.getMotto();
        if(null == motto){
            getMottosFromDB();
            modelMap.put("motto",CacheUtils.getMotto());
        }else{
            modelMap.put("motto",motto);
            long mottoCount = metasService.getCountByType("MOTTO");
            if(mottoCount != CacheUtils.getMottoSize()){
                CacheUtils.removeAllMotto();
                CacheUtils.times = null;
                getMottosFromDB();
            }
        }
    }

    private void getMottosFromDB(){
        PageInputDTO<MetasInputData> pageInputDTO = new PageInputDTO<>();
        MetasInputData metasInputData = new MetasInputData();
        metasInputData.setType("MOTTO");
        pageInputDTO.setData(metasInputData);
        pageInputDTO.setPageSize(20);
        PageOutputDTO<MetasOutputData> mottos = metasService.getMetasByType(pageInputDTO);
        int i = 1;
        if(null != mottos && 0 <mottos.getData().size()){
            for(MetasOutputData metasOutputData : mottos.getData()){
                CacheUtils.putMotto(i,metasOutputData);
                i++;
            }
        }
    }

    @Autowired
    private CommentsService commentsService;

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

        //查询文章对应的评论信息
        PageOutputDTO<CommentsOutputData> commentsByCid = commentsService.getCommentsByCid(cid);
        modelMap.put("comments",commentsByCid);

        link(modelMap);
        latestArticle(modelMap);
        userShowInformation(modelMap);
        getMotto(modelMap);
        return "post";
    }

    //新增一条评论
    @RequestMapping(path = "/index/comment", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage<String> addComment(@RequestBody PojoInputDTO<CommentsInputData> pojoInputDTO){

        //去除评论中的@信息
       /* if(null != pojoInputDTO.getData()){
            String content = pojoInputDTO.getData().getContent();
            String[] split = content.split(":");
            if(split.length>1){
                StringBuilder stringBuilder = new StringBuilder();
                for(int i=1;i<split.length;i++){
                    stringBuilder.append(split[i]);
                }
                pojoInputDTO.getData().setContent(stringBuilder.toString());
            }
        }*/

        PojoOutputDTO pojoOutputDTO = commentsService.addComment(pojoInputDTO);

        if("SOA0000".equals(pojoOutputDTO.getCode())){
            return new ReturnMessage<>(0,"success");
        }else{
            return new ReturnMessage<>(1,"failure");
        }
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
        PageOutputDTO<ContentsOutputData> contentsByPage = contentsService.getContentsByPage(pageInputDTO);

        for(ContentsOutputData contentsOutputData : contentsByPage.getData()){
            contentsOutputData.setContent(ThemeUtils.cutArticle(contentsOutputData.getContent()));
        }

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
        link(modelMap);
        latestArticle(modelMap);
        userShowInformation(modelMap);
        getMotto(modelMap);
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
        PojoOutputDTO<MetasOutputData> pojoOutputDTO = new PojoOutputDTO<>();
        if(null != pageOutputDTO){
            String content = ThemeUtils.articleTransfer(pageOutputDTO.getData().get(0).getContent());
             pageOutputDTO.getData().get(0).setContent(content);
        }
        pojoOutputDTO.setData(pageOutputDTO.getData().get(0));
        modelMap.addAttribute("content",pojoOutputDTO);

        //查询文章对应的评论信息
        PageOutputDTO<CommentsOutputData> commentsByCid = null;
        if(null != pageOutputDTO && null != pageOutputDTO.getData()){
            commentsByCid = commentsService.getCommentsByCid(pageOutputDTO.getData().get(0).getMid());
        }
        modelMap.put("comments",commentsByCid);

        link(modelMap);
        latestArticle(modelMap);
        userShowInformation(modelMap);
        getMotto(modelMap);
        return "about";
    }


}
