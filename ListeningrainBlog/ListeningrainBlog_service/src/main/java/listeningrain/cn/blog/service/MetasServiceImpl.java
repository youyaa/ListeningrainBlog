package listeningrain.cn.blog.service;

import listeningrain.cn.blog.atomservice.AtomMetasService;
import listeningrain.cn.blog.entity.Metas;
import listeningrain.cn.blog.input.data.MetasInputData;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.MetasOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.service.api.MetasService;
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
    public PageOutputDTO<MetasOutputData> getAllLinks(PojoInputDTO<MetasInputData> pojoInputDTO) {
        //此处不需要
        List<Metas> allMetas = atomMetasService.getAllMetas(new Metas());

        //构建返回对象
        PageOutputDTO<MetasOutputData> pageOutputDTO = new PageOutputDTO<>();
        List<MetasOutputData> list = new ArrayList<>();

        if(null != allMetas && 0<allMetas.size()){
            for (Metas metas : allMetas){
                MetasOutputData metasOutputData = new MetasOutputData();
                BeanUtils.copyProperties(metas,metasOutputData);
                list.add(metasOutputData);
            }
        }
        pageOutputDTO.setData(list);
        return pageOutputDTO;
    }
}
