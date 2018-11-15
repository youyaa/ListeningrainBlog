package listeningrain.cn.blog.atomservice.impl;

import listeningrain.cn.blog.atomservice.AtomMetasService;
import listeningrain.cn.blog.dao.MetasMapper;
import listeningrain.cn.blog.entity.Metas;
import listeningrain.cn.blog.entity.MetasExample;
import listeningrain.cn.blog.output.data.AdminIndexOutputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if("LINK".equals(metas.getType())){
            metasExample.setOrderByClause("sort ASC");
        }
        metasExample.setOrderByClause("created DESC");
        metasExample.createCriteria().andTypeEqualTo(metas.getType());
        List<Metas> metasLINK = metasMapper.selectByExample(metasExample);
        return metasLINK;
    }

    @Override
    public Metas getMetasById(Metas metas) {
        MetasExample metasExample = new MetasExample();
        metasExample.createCriteria().andMidEqualTo(metas.getMid());
        List<Metas> metasLINK = metasMapper.selectByExample(metasExample);
        if(null != metasLINK && metasLINK.size()>0){
            return metasLINK.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public int updateMetaById(Metas metas) {
        int i = metasMapper.updateByPrimaryKeySelective(metas);
        return i;
    }

    @Override
    @Transactional
    public int addMeta(Metas metas) {
        int i = metasMapper.insertSelective(metas);
        return i;
    }

    @Override
    @Transactional
    public int deleteMetaById(Integer mid) {
        int i = metasMapper.deleteByPrimaryKey(mid);
        return i;
    }

    @Override
    public long getCountByType(String type){
        MetasExample metasExample = new MetasExample();
        metasExample.createCriteria().andTypeEqualTo(type);
        long count = metasMapper.countByExample(metasExample);
        return  count;
    }

    @Override
    public AdminIndexOutputData getAdminIndexLink() {
        int todayCount = metasMapper.selectTodayLinkCount();
        int totalCount = metasMapper.selectLinkCount();
        AdminIndexOutputData adminIndexOutputData = new AdminIndexOutputData();
        adminIndexOutputData.setTotalCount(totalCount);
        adminIndexOutputData.setTodayCount(todayCount);
        return adminIndexOutputData;
    }
}
