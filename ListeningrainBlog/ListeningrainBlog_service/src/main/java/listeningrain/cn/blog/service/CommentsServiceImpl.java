package listeningrain.cn.blog.service;

import listeningrain.cn.blog.atomservice.AtomCommentsService;
import listeningrain.cn.blog.constant.ReturnErrCodeEnum;
import listeningrain.cn.blog.entity.Comments;
import listeningrain.cn.blog.exception.BlogServiceException;
import listeningrain.cn.blog.input.data.CommentsInputData;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.CommentsOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.CommentsService;
import listeningrain.cn.blog.utils.ThemeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/13 14:58
 * Description:
 */
@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private AtomCommentsService atomCommentsService;

    @Override
    public PageOutputDTO<CommentsOutputData> getCommentsByCid(Integer cid) {
        List<Comments> comments = atomCommentsService.selectCommentsByCid(cid);
        PageOutputDTO<CommentsOutputData> pageOutputDTO = null;
        List<CommentsOutputData> list = new ArrayList<>(comments.size());
        if(null != comments && 0 < comments.size()){
            pageOutputDTO = new PageOutputDTO<>();
            for(Comments comment : comments){
                CommentsOutputData commentsOutputData = new CommentsOutputData();
                BeanUtils.copyProperties(comment,commentsOutputData);
                commentsOutputData.setCreated(ThemeUtils.formate(comment.getCreated()));
                //根据每一条记录的id去查询其下的子评论
                List<Comments> childrenComments = atomCommentsService.selectCommentsByTopLevelId(comment.getCoid());
                //循环处理评论之间的回复关系
                List<CommentsOutputData> childrenCommentOutput = null;
                if(null != childrenComments && 0 < childrenComments.size()){
                    childrenCommentOutput = new ArrayList<>(10);
                    for(Comments chilComment : childrenComments){
                        CommentsOutputData commentsOutputData1 = new CommentsOutputData();
                        BeanUtils.copyProperties(chilComment,commentsOutputData1);

                        //存在评论的回复关系
                        if(-1 != chilComment.getParent()){
                            boolean flag = false;
                            for(Comments chilComment1 : childrenComments){
                                if(chilComment.getParent() == chilComment1.getCoid()){
                                    commentsOutputData1.setParentAuthorName(chilComment1.getAuthor());
                                    flag = true;
                                    break;
                                }
                            }

                            if(!flag){
                                for(Comments chilComment1 : comments){
                                    if(chilComment.getParent() == chilComment1.getCoid()){
                                        commentsOutputData1.setParentAuthorName(chilComment1.getAuthor());
                                        break;
                                    }
                                }
                            }

                        }
                        childrenCommentOutput.add(commentsOutputData1);
                    }
                    commentsOutputData.setChildrenComments(childrenCommentOutput);
                }
                list.add(commentsOutputData);
            }
            pageOutputDTO.setData(list);
        }
        return pageOutputDTO;
    }

    @Override
    public PojoOutputDTO addComment(PojoInputDTO<CommentsInputData> pojoInputDTO) {
        Comments comments = new Comments();
        if(null != pojoInputDTO && null != pojoInputDTO.getData()){
            BeanUtils.copyProperties(pojoInputDTO.getData(),comments);
        }
        int i = atomCommentsService.insertComment(comments);
        if(i<0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_INSERT);
        }
        return new PojoOutputDTO();
    }
}
