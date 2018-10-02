package listeningrain.cn.blog.input.data;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * author: listeningrain
 * Date: 2018/9/24
 * Time: 10:07
 * Description:
 */
@Getter
@Setter
public class ContentsInputData extends CommonInputData{

    private Integer cid;

    private String title;

    private String slug;

    private Timestamp created;

    private Integer modified;

    private Integer authorId;

    private String type;

    private String status;

    private String tags;

    private String categories;

    private Integer hits;

    private Integer commentsNum;

    private Boolean allowComment;

    private Boolean allowPing;

    private Boolean allowFeed;

    private String address;

    private String osName;

    private String browser;

    private String content;

}
