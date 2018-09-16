package listeningrain.cn.blog.service;

import listeningrain.cn.blog.dao.TtestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 19:31
 * Description:
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService{

    @Autowired
    private TtestMapper ttestMapper;

    @Override
    public String getName() {
        String s = ttestMapper.find();
        return s;
    }
}
