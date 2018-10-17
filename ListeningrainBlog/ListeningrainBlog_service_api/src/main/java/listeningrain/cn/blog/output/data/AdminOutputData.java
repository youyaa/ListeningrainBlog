package listeningrain.cn.blog.output.data;

import lombok.Getter;
import lombok.Setter;

/**
 * author: listeningrain
 * Date: 2018/10/4
 * Time: 11:04
 * Description:
 */
@Setter
@Getter
public class AdminOutputData extends CommonOutputData {

    private Integer uid;

    private String username;

    private String password;

    private String email;

    private String homeUrl;

    private String screenName;

    private Integer created;

    private Integer activated;

    private Integer logged;

    private String groupName;

}
