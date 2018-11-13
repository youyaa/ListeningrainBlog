package listeningrain.cn.blog.service.api;

import listeningrain.cn.blog.input.data.CommentsInputData;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.CommentsOutputData;
import listeningrain.cn.blog.output.dto.PageOutputDTO;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/13 14:55
 * Description:
 */
public interface CommentsService {

    PageOutputDTO<CommentsOutputData> getCommentsByCid(Integer cid);

    PojoOutputDTO addComment(PojoInputDTO<CommentsInputData> pojoInputDTO);
}
