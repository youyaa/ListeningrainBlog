package listeningrain.cn.blog.atomservice;

import listeningrain.cn.blog.entity.Metas;

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
     * 新增
     */
    int addMeta(Metas metas);
}
