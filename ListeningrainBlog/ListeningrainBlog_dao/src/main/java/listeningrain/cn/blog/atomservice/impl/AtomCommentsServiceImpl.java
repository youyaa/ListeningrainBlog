package listeningrain.cn.blog.atomservice.impl;

import listeningrain.cn.blog.atomservice.AtomCommentsService;
import listeningrain.cn.blog.dao.CommentsMapper;
import listeningrain.cn.blog.entity.Comments;
import listeningrain.cn.blog.entity.CommentsExample;
import listeningrain.cn.blog.output.data.AdminIndexOutputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/13 14:51
 * Description:
 */
@Service("atomCommentsService")
public class AtomCommentsServiceImpl implements AtomCommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public List<Comments> selectCommentsByCid(Integer cid) {
        CommentsExample commentsExample = new CommentsExample();
        //查询该文章下的所有的一级评论
        commentsExample.createCriteria().andCidEqualTo(cid).andTopLevelIdEqualTo(-1);
        List<Comments> comments = commentsMapper.selectByExampleWithBLOBs(commentsExample);
        return comments;
    }

    @Override
    public List<Comments> selectCommentsByTopLevelId(Integer topLevelId) {
        //查询一级评论下的所有子评论
        CommentsExample commentsExample = new CommentsExample();
        commentsExample.createCriteria().andTopLevelIdEqualTo(topLevelId);
        List<Comments> comments = commentsMapper.selectByExampleWithBLOBs(commentsExample);
        return comments;
    }

    @Override
    @Transactional
    public int insertComment(Comments comments) {
        int i = commentsMapper.insertSelective(comments);
        return i;
    }

    @Override
    public List<Comments> getComments(Comments comments) {
        CommentsExample commentsExample = new CommentsExample();
        commentsExample.setOrderByClause("created DESC");
        List<Comments> result = commentsMapper.selectByExampleWithBLOBs(commentsExample);
        return result;
    }

    @Override
    public AdminIndexOutputData selectAdminIndexComment() {
        int todayCount = commentsMapper.selectTodayCommentCount();
        int totalCount = commentsMapper.selectCommentCount();
        AdminIndexOutputData adminIndexOutputData = new AdminIndexOutputData();
        adminIndexOutputData.setTotalCount(totalCount);
        adminIndexOutputData.setTodayCount(todayCount);
        return adminIndexOutputData;
    }
}
