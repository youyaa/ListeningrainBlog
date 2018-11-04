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
     * 获取所有的友链
     * @param metas
     * @return
     */
    List<Metas> getAllMetas(Metas metas);

    /**
     * 根据id获取友链
     */
    Metas getMetasById(Metas metas);

    /**
     * 更新友链
     */
    int updateMetaById(Metas metas);
}
