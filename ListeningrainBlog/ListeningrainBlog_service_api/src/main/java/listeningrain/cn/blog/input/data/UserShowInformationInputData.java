package listeningrain.cn.blog.input.data;

import lombok.Getter;
import lombok.Setter;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/12 17:30
 * Description:
 */
@Getter
@Setter
public class UserShowInformationInputData extends CommonInputData{

    private Integer id;

    private Integer userId;

    private String nickName;

    private Integer age;

    private String address;

    private String famousSays;

    private Integer follower;

    private String touxiang;

}
