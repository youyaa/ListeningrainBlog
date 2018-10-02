package listeningrain.cn.blog.atomservice.impl;

import listeningrain.cn.blog.atomservice.AtomMetasService;
import listeningrain.cn.blog.dao.MetasMapper;
import listeningrain.cn.blog.entity.Metas;
import listeningrain.cn.blog.entity.MetasExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/10/1
 * Time: 16:08
 * Description:
 */
@Service("atomMetasService")
public class AtomMetasServiceImpl implements AtomMetasService {

    @Autowired
    private MetasMapper metasMapper;

    @Override
    public List<Metas> getAllMetas(Metas metas) {
        MetasExample metasExample = new MetasExample();
        metasExample.createCriteria().andTypeEqualTo("LINK");
        List<Metas> metasLINK = metasMapper.selectByExample(metasExample);
        return metasLINK;
    }
}
