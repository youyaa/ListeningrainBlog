package listeningrain.cn.blog.input.data;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/13 14:55
 * Description:
 */
@Setter
@Getter
public class CommentsInputData extends CommonInputData{

    private Integer coid;

    private Integer cid;

    private Integer parent;

    private Integer topLevelId;

    private Timestamp created;

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
}
