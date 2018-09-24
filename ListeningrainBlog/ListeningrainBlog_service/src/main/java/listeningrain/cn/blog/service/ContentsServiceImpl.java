package listeningrain.cn.blog.service;

import com.github.pagehelper.PageHelper;
import listeningrain.cn.blog.atomservice.AtomContensService;
import listeningrain.cn.blog.entity.Contents;
import listeningrain.cn.blog.input.data.ContentsInputData;
import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.output.data.ContentsOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
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

        pageOutputDTO.setData(list);
        return pageOutputDTO;
    }
}
