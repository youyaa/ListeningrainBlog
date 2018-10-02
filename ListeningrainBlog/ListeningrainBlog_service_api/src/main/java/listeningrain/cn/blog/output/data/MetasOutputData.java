package listeningrain.cn.blog.output.data;

import lombok.Getter;
import lombok.Setter;

/**
 * author: listeningrain
 * Date: 2018/10/1
 * Time: 16:14
 * Description:
 */
@Getter
@Setter
public class MetasOutputData extends CommonOutputData {
    private Integer mid;

    private String name;

    private String slug;

    private String type;

    private String description;

    private Integer sort;

    private Integer parent;

    private Byte status;

    private String content;
}
