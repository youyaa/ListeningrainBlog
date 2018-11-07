package listeningrain.cn.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import listeningrain.cn.blog.atomservice.AtomMetasService;
import listeningrain.cn.blog.constant.ReturnErrCodeEnum;
import listeningrain.cn.blog.entity.Metas;
import listeningrain.cn.blog.exception.BlogServiceException;
import listeningrain.cn.blog.input.data.MetasInputData;
import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.MetasOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.MetasService;
import listeningrain.cn.blog.utils.ThemeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/10/1
 * Time: 16:12
 * Description:
 */
@Service("metasService")
public class MetasServiceImpl implements MetasService {

    @Autowired
    private AtomMetasService atomMetasService;


    @Override
    public PageOutputDTO<MetasOutputData> getMetasByType(PageInputDTO<MetasInputData> pageInputDTO) {
        Metas metas = new Metas();
        metas.setType(pageInputDTO.getData().getType());
        PageHelper.startPage(pageInputDTO.getPageNum(),pageInputDTO.getPageSize());
        List<Metas> allMetas = atomMetasService.getAllMetas(metas);
        PageInfo<Metas> of = PageInfo.of(allMetas);
        //构建返回对象
        PageOutputDTO<MetasOutputData> pageOutputDTO = new PageOutputDTO<>();
        List<MetasOutputData> list = new ArrayList<>();

        if(null != allMetas && 0<allMetas.size()){
            for (Metas meta : allMetas){
                MetasOutputData metasOutputData = new MetasOutputData();
                if("MARKDOWN".equals(meta.getDescription())){
                    String content = ThemeUtils.articleTransfer(meta.getContent());
                    meta.setContent(content);
                }
                BeanUtils.copyProperties(meta,metasOutputData);
                list.add(metasOutputData);
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
        pageOutputDTO.setData(list);

        //设置总页数
        pageOutputDTO.setTotalPageNum(pageBar.length);
        return pageOutputDTO;
    }

    @Override
    public PojoOutputDTO<MetasOutputData> getMetasById(PojoInputDTO<MetasInputData> pojoInputDTO) {
        Metas metas = new Metas();
        metas.setMid(pojoInputDTO.getData().getMid());
        PojoOutputDTO<MetasOutputData> pojoOutputDTO = new PojoOutputDTO<>();
        MetasOutputData metasOutputData = new MetasOutputData();
        Metas metasById = atomMetasService.getMetasById(metas);
        BeanUtils.copyProperties(metasById,metasOutputData);
        pojoOutputDTO.setData(metasOutputData);
        return pojoOutputDTO;
    }

    @Override
    public PojoOutputDTO updateMetas(PojoInputDTO<MetasInputData> pojoInputDTO) {
        Metas metas = new Metas();
        if(null != pojoInputDTO.getData()){
            BeanUtils.copyProperties(pojoInputDTO.getData(),metas);
        }
        int i = atomMetasService.updateMetaById(metas);
        if(i <= 0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_UPDATE);
        }
        return new PojoOutputDTO();
    }

    @Override
    public PojoOutputDTO addMetas(PojoInputDTO<MetasInputData> pojoInputDTO) {
        Metas metas = new Metas();
        BeanUtils.copyProperties(pojoInputDTO.getData(),metas);
        int i = atomMetasService.addMeta(metas);
        if(i <= 0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_INSERT);
        }
        return new PojoOutputDTO();
    }

    @Override
    public PojoOutputDTO deleteMetasById(PojoInputDTO<MetasInputData> pojoInputDTO) {
        Integer mid = null;
        if(null != pojoInputDTO){
            mid = pojoInputDTO.getData().getMid();
        }
        int i = atomMetasService.deleteMetaById(mid);
        if(i <= 0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_DELETE);
        }
        return new PojoOutputDTO();
    }
}
