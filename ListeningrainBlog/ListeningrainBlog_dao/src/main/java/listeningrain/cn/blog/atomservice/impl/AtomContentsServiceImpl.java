package listeningrain.cn.blog.atomservice.impl;

import listeningrain.cn.blog.atomservice.AtomContensService;
import listeningrain.cn.blog.dao.ContentsMapper;
import listeningrain.cn.blog.entity.Contents;
import listeningrain.cn.blog.entity.ContentsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/9/24
 * Time: 9:47
 * Description:
 */
@Service("atomContentsService")
public class AtomContentsServiceImpl implements AtomContensService {

    @Autowired
    private ContentsMapper contentsMapper;

    /**
     * 分页查询
     * @return
     */
    public List<Contents> getContentsByPage(){
       ContentsExample contentsExample = new ContentsExample();
       contentsExample.setOrderByClause("created DESC");
        List<Contents> contents = contentsMapper.selectByExampleWithBLOBs(contentsExample);
        return contents;
    }


    @Override
    public Contents getContentsById(Integer id) {
        Contents contents = contentsMapper.selectByPrimaryKey(id);
        return contents;
    }

    @Override
    public Integer insertContent(Contents contents) {
        int i = contentsMapper.insertSelective(contents);
        return i;
    }
}
