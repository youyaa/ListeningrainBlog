package listeningrain.cn.blog.service;

import listeningrain.cn.blog.atomservice.AtomMetasService;
import listeningrain.cn.blog.constant.ReturnErrCodeEnum;
import listeningrain.cn.blog.entity.Metas;
import listeningrain.cn.blog.exception.BlogServiceException;
import listeningrain.cn.blog.input.data.MetasInputData;
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
    public PageOutputDTO<MetasOutputData> getMetasByType(PojoInputDTO<MetasInputData> pojoInputDTO) {
        Metas metas = new Metas();
        metas.setType(pojoInputDTO.getData().getType());
        List<Metas> allMetas = atomMetasService.getAllMetas(metas);

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
        pageOutputDTO.setData(list);
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
}
