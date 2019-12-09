package listeningrain.cn.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import listeningrain.cn.blog.atomservice.AtomContensService;
import listeningrain.cn.blog.atomservice.AtomMetasService;
import listeningrain.cn.blog.constant.ConstantsEnum;
import listeningrain.cn.blog.constant.ReturnErrCodeEnum;
import listeningrain.cn.blog.entity.Contents;
import listeningrain.cn.blog.entity.Metas;
import listeningrain.cn.blog.exception.BlogServiceException;
import listeningrain.cn.blog.input.data.ContentsInputData;
import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.AdminIndexOutputData;
import listeningrain.cn.blog.output.data.ContentsOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.ContentsService;
import listeningrain.cn.blog.utils.ThemeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/9/24
 * Time: 10:23
 * Description:
 */
@Service("contentsService")
public class ContentsServiceImpl implements ContentsService {

    @Autowired
    private AtomContensService atomContensService;
    @Autowired
    private AtomMetasService atomMetasService;

    //分页查询
    public PageOutputDTO<ContentsOutputData> getContentsByPage(PageInputDTO<ContentsInputData> pageInputDTO){
        Contents content = new Contents();
        if(null != pageInputDTO && null != pageInputDTO.getData()){
            BeanUtils.copyProperties(pageInputDTO.getData(),content);
        }
        PageHelper.startPage(pageInputDTO.getPageNum(),pageInputDTO.getPageSize());
        List<Contents> contentsByPage = atomContensService.getContentsByPage(content);
        PageInfo<Contents> of = PageInfo.of(contentsByPage);
        PageOutputDTO<ContentsOutputData> pageOutputDTO = new PageOutputDTO<>();

        List<ContentsOutputData> list = new ArrayList<>();

        /**
         * TODO 此处待优化
         */
        if(null != contentsByPage && 0<contentsByPage.size()){
               for(Contents contents: contentsByPage){
                   ContentsOutputData contentsOutputData = new ContentsOutputData();
                   BeanUtils.copyProperties(contents,contentsOutputData);
                   contentsOutputData.setCreated(ThemeUtils.formate(contents.getCreated()));
                   contentsOutputData.setModified(ThemeUtils.formate(contents.getModified()));
                   Metas metas = new Metas();
                   metas.setMid(Integer.valueOf(contents.getCategories()));
                   Metas metasById = atomMetasService.getMetasById(metas);
                   if(null != metasById){
                       contentsOutputData.setCategories(metasById.getName());
                   }
                   //contentsOutputData.setStatus(ArticleStatusEnum.getMsg(contents.getStatus()));
                   list.add(contentsOutputData);
               }
        }

        //设置其他参数
        pageOutputDTO.setPageNum(pageInputDTO.getPageNum());
        pageOutputDTO.setPageSize(pageInputDTO.getPageSize());
        pageOutputDTO.setTotal((int)of.getTotal());
        pageOutputDTO.setData(list);

        //设置分页参数
        Integer pageNum = null;
        if(pageOutputDTO.getTotal() % pageOutputDTO.getPageSize() == 0){
          pageNum = pageOutputDTO.getTotal() / pageOutputDTO.getPageSize();
        }else{
            pageNum = pageOutputDTO.getTotal() / pageOutputDTO.getPageSize() +1;
        }

        Integer[] pageBar = new Integer[pageNum];
        for(int i =1;i<=pageNum;i++){
            pageBar[i-1] = i;
        }
        pageOutputDTO.setPageBar(pageBar);
        //设置总页数
        pageOutputDTO.setTotalPageNum(pageBar.length);
        return pageOutputDTO;
    }

    //根据id查询文章详情页
    public PojoOutputDTO<ContentsOutputData> getContentsById(PojoInputDTO<ContentsInputData> pojoInputDTO){

            Contents content = atomContensService.getContentsById(pojoInputDTO.getData().getCid());

            PojoOutputDTO<ContentsOutputData>  response = null;
            if(null != content){
                //构造返回对象
                ContentsOutputData contentsOutputData = new ContentsOutputData();
                BeanUtils.copyProperties(content,contentsOutputData);
                contentsOutputData.setCreated(ThemeUtils.formate(content.getCreated()));
                response = new PojoOutputDTO<>();
                response.setData(contentsOutputData);
            }

            //异步更新文章的浏览量
            new Thread(()->{
                atomContensService.updateContentsHitsById(pojoInputDTO.getData().getCid());
            }).start();

            return response;
    }

    @Override
    public PojoOutputDTO addContent(PojoInputDTO<ContentsInputData> pojoInputDTO) {
        Contents contents = new Contents();
        BeanUtils.copyProperties(pojoInputDTO.getData(),contents);
        if(StringUtils.isEmpty(contents.getCategories())){
            contents.setCategories(ConstantsEnum.DEFAULT_CATEGORY);
        }
        Integer integer = atomContensService.insertContent(contents);
        if(integer<0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_INSERT);
        }
        return new PojoOutputDTO();
    }

    @Override
    public PojoOutputDTO deleteContent(PojoInputDTO<ContentsInputData> pojoInputDTO) {
        Contents contents = new Contents();
        if(null != pojoInputDTO){
            contents.setCid(pojoInputDTO.getData().getCid());
        }

        Integer integer = atomContensService.deleteContent(contents);
        if(integer<0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_DELETE);
        }
        return new PojoOutputDTO();
    }

    @Override
    public PojoOutputDTO updateContent(PojoInputDTO<ContentsInputData> pojoInputDTO) {
        Contents contents = new Contents();
        if(null != pojoInputDTO){
            BeanUtils.copyProperties(pojoInputDTO.getData(),contents);
        }
        Integer integer = atomContensService.updateContent(contents);
        if(integer<0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_UPDATE);
        }
        return new PojoOutputDTO();
    }

    @Override
    public AdminIndexOutputData getAdminIndexContentsCount() {
        AdminIndexOutputData adminIndexOutputData = atomContensService.selectContentCount();
        return adminIndexOutputData;
    }
}
