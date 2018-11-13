package listeningrain.cn.blog.service.api;

import listeningrain.cn.blog.input.data.MetasInputData;
import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.MetasOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;

/**
 * author: listeningrain
 * Date: 2018/10/1
 * Time: 16:13
 * Description:
 */
public interface MetasService {

    /**
     * 获取所有的
     */
    PageOutputDTO<MetasOutputData> getMetasByType(PageInputDTO<MetasInputData> pageInputDTO);

    /**
     * 根据id获取
     */
    PojoOutputDTO<MetasOutputData> getMetasById(PojoInputDTO<MetasInputData> pojoInputDTO);

    PojoOutputDTO updateMetas(PojoInputDTO<MetasInputData> pojoInputDTO);

    PojoOutputDTO deleteMetasById(PojoInputDTO<MetasInputData> pojoInputDTO);

    PojoOutputDTO addMetas(PojoInputDTO<MetasInputData> pojoInputDTO);

    long getCountByType(String type);
}
