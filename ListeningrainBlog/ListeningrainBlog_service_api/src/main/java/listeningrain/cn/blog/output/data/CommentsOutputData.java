package listeningrain.cn.blog.output.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/13 14:55
 * Description:
 */
@Setter
@Getter
public class CommentsOutputData extends CommonOutputData{

    private Integer coid;

    private Integer cid;

    private Integer parent;

    private Integer topLevelId;

    private String created;

    private String author;

    private String avator;

    private Integer authorId;

    private Integer ownerId;

    private String mail;

    private String url;

    private String ip;

    private String agent;

    private String type;

    private String status;

    private String osName;

    private String address;

    private String browser;

    private String content;

    private String parentAuthorName;

    //用户的头像
    private String avatar;

    private List<CommentsOutputData> childrenComments;
}
