package listeningrain.cn.blog.service.api;

import listeningrain.cn.blog.input.data.MetasInputData;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.MetasOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;

/**
 * author: listeningrain
 * Date: 2018/10/1
 * Time: 16:13
 * Description:
 */
public interface MetasService {

    /**
     * 获取所有的友链
     */
    PageOutputDTO<MetasOutputData> getMetasByType(PojoInputDTO<MetasInputData> pojoInputDTO);
}
