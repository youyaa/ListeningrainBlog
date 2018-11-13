package listeningrain.cn.blog.atomservice;

import listeningrain.cn.blog.entity.Comments;

import java.util.List;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/13 14:50
 * Description:
 */
public interface AtomCommentsService {

    List<Comments> selectCommentsByCid(Integer cid);

    List<Comments> selectCommentsByTopLevelId(Integer topLevelId);

    int insertComment(Comments comments);


}
