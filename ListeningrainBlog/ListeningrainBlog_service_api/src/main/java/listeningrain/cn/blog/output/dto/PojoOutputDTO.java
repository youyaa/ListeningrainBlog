package listeningrain.cn.blog.output.dto;

import listeningrain.cn.blog.output.data.CommonOutputData;
import lombok.Getter;
import lombok.Setter;

/**
 * author: listeningrain
 * Date: 2018/9/29
 * Time: 22:32
 * Description: 只携带一个bean的输出DTO
 */
@Getter
@Setter
public class PojoOutputDTO<T extends CommonOutputData> extends CommonOutputDTO{

    private T data;
}
