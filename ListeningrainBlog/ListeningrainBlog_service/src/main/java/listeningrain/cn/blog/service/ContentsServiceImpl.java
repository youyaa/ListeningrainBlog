package listeningrain.cn.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import listeningrain.cn.blog.atomservice.AtomContensService;
import listeningrain.cn.blog.entity.Contents;
import listeningrain.cn.blog.input.data.ContentsInputData;
import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.ContentsOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.ContentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //分页查询
    public PageOutputDTO<ContentsOutputData> getContentsByPage(PageInputDTO<ContentsInputData> pageInputDTO){
        PageHelper.startPage(pageInputDTO.getPageNum(),pageInputDTO.getPageSize());
        List<Contents> contentsByPage = atomContensService.getContentsByPage();
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
                response = new PojoOutputDTO<>();
                response.setData(contentsOutputData);
            }

            return response;

    }
}
