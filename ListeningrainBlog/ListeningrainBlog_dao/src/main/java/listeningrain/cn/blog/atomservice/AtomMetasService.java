package listeningrain.cn.blog.atomservice;

import listeningrain.cn.blog.entity.Metas;
import listeningrain.cn.blog.output.data.AdminIndexOutputData;

import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/10/1
 * Time: 16:07
 * Description:
 */
public interface AtomMetasService {

    /**
     * 获取所有的
     * @param metas
     * @return
     */
    List<Metas> getAllMetas(Metas metas);

    /**
     * 根据id获取
     */
    Metas getMetasById(Metas metas);

    /**
     * 更新
     */
    int updateMetaById(Metas metas);

    /**
     * 删除
     */
    int deleteMetaById(Integer mid);

    /**
     * 新增
     */
    int addMeta(Metas metas);

    long getCountByType(String type);

    AdminIndexOutputData getAdminIndexLink();
}
