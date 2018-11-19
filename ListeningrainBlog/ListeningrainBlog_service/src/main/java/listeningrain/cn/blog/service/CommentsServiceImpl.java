package listeningrain.cn.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import listeningrain.cn.blog.atomservice.AtomCommentsService;
import listeningrain.cn.blog.atomservice.AtomContensService;
import listeningrain.cn.blog.constant.ReturnErrCodeEnum;
import listeningrain.cn.blog.entity.Comments;
import listeningrain.cn.blog.exception.BlogServiceException;
import listeningrain.cn.blog.input.data.CommentsInputData;
import listeningrain.cn.blog.input.dto.PageInputDTO;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.AdminIndexOutputData;
import listeningrain.cn.blog.output.data.CommentsOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.CommentsService;
import listeningrain.cn.blog.utils.EncryptUtils;
import listeningrain.cn.blog.utils.ThemeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
                commentsOutputData.setAvator(gravatar(comment.getMail()));

                //根据每一条记录的id去查询其下的子评论
                List<Comments> childrenComments = atomCommentsService.selectCommentsByTopLevelId(comment.getCoid());
                //循环处理评论之间的回复关系
                List<CommentsOutputData> childrenCommentOutput = null;
                if(null != childrenComments && 0 < childrenComments.size()){
                    childrenCommentOutput = new ArrayList<>(10);
                    for(Comments chilComment : childrenComments){
                        CommentsOutputData commentsOutputData1 = new CommentsOutputData();
                        BeanUtils.copyProperties(chilComment,commentsOutputData1);
                        commentsOutputData1.setAvator(gravatar(chilComment.getMail()));
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

    /**
     * 返回gravatar头像地址
     *
     * @param email
     * @return
     */
    private String gravatar(String email) {
        String avatarUrl = "https://cn.gravatar.com/avatar";
        if (StringUtils.isEmpty(email)) {
            return avatarUrl;
        }
        String hash = EncryptUtils.MD5(email.trim().toLowerCase());
        return avatarUrl + "/" + hash;
    }

    @Autowired
    private AtomContensService atomContensService;

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

        //异步更新文章的评论数
        new Thread(()->{
            atomContensService.updateCommentAddCountById(comments.getCid());
        }).start();

        return new PojoOutputDTO();
    }

    @Override
    public PojoOutputDTO deleteComment(PojoInputDTO<CommentsInputData> pojoInputDTO) {
        Comments comments = new Comments();
        comments.setCoid(pojoInputDTO.getData().getCoid());
        int i = atomCommentsService.deleteComment(comments);
        if(i<0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_DELETE);
        }

        //异步更新文章的评论数
        new Thread(()->{
            atomContensService.updateCommentDecrCountById(pojoInputDTO.getData().getCid());
        }).start();
        return new PojoOutputDTO();
    }

    @Override
    public PageOutputDTO<CommentsOutputData> getCommentsByPage(PageInputDTO pageInputDTO) {
        PageHelper.startPage(pageInputDTO.getPageNum(),pageInputDTO.getPageSize());
        List<Comments> comments = atomCommentsService.getComments(new Comments());
        PageOutputDTO<CommentsOutputData> pageOutputDTO = null;
        List<CommentsOutputData> list = null;
        PageInfo<Comments> of = PageInfo.of(comments);
        if(null != comments && 0 < comments.size()){
            pageOutputDTO = new PageOutputDTO<>();
            list = new ArrayList<>();
            for(Comments comments1 : comments){
                CommentsOutputData commentsOutputData = new CommentsOutputData();
                BeanUtils.copyProperties(comments1,commentsOutputData);
                commentsOutputData.setCreated(ThemeUtils.formate(comments1.getCreated()));
                commentsOutputData.setAvator(gravatar(comments1.getMail()));
                list.add(commentsOutputData);
            }
            pageOutputDTO.setData(list);
            //设置其他参数
            pageOutputDTO.setPageNum(pageInputDTO.getPageNum());
            pageOutputDTO.setPageSize(pageInputDTO.getPageSize());
            pageOutputDTO.setTotal((int)of.getTotal());

            //设置分页参数
            Integer pageNum = null;
            if(pageOutputDTO.getTotal() % pageOutputDTO.getPageSize() == 0){
                pageNum = pageOutputDTO.getTotal() / pageOutputDTO.getPageSize();
            }else{
                pageNum = pageOutputDTO.getTotal() / pageOutputDTO.getPageSize() +1;
            }

            Integer[] pageBar = new Integer[pageNum];
            for(int i =1;i<=pageNum;i++){
                pageBar[i-1] = i;
            }
            pageOutputDTO.setPageBar(pageBar);
            //设置总页数
            pageOutputDTO.setTotalPageNum(pageBar.length);
        }

        return pageOutputDTO;
    }

    @Override
    public AdminIndexOutputData getAdminIndexComment() {
        AdminIndexOutputData adminIndexOutputData = atomCommentsService.selectAdminIndexComment();
        return adminIndexOutputData;
    }
}
