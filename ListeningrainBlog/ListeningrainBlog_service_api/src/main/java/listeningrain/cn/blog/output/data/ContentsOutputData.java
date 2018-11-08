package listeningrain.cn.blog.output.data;

import lombok.Getter;
import lombok.Setter;

/**
 * author: listeningrain
 * Date: 2018/9/24
 * Time: 10:32
 * Description:
 */
@Getter
@Setter
public class ContentsOutputData extends CommonOutputData{

    private Integer cid;

    private String title;

    private String slug;

    private String created;

    private String modified;

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
