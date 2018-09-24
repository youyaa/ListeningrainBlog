package listeningrain.cn.blog.atomservice;

import listeningrain.cn.blog.entity.Contents;

import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/9/24
 * Time: 9:48
 * Description:
 */
public interface AtomContensService {

    /**
     * 分页查询
     * @return
     */
    List<Contents> getContentsByPage();

}
