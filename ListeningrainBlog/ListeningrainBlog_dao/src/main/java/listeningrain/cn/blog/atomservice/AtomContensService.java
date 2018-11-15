package listeningrain.cn.blog.atomservice;

import listeningrain.cn.blog.entity.Contents;
import listeningrain.cn.blog.output.data.AdminIndexOutputData;

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
     *
     * @return
     */
    List<Contents> getContentsByPage(Contents contents);

    /**
     * 根据id查询文章
     */
    Contents getContentsById(Integer id);

    /**
     * 增加一篇文章
     */
    Integer insertContent(Contents contents);

    /**
     * 删除文章
     */
    Integer deleteContent(Contents contents);

    /**
     * 修改文章
     */
    Integer updateContent(Contents contents);

    /**
     * 文章评论数+1
     */
    Integer updateCommentCountById(Integer cid);

    /**
     * 后台管理首页需要的文章聚合数据
     */
    AdminIndexOutputData selectContentCount();
}

