package listeningrain.cn.blog.input.dto;

import listeningrain.cn.blog.input.data.CommonInputData;
import lombok.Getter;
import lombok.Setter;

/**
 * author: listeningrain
 * Date: 2018/9/29
 * Time: 22:36
 * Description:
 */
@Setter
@Getter
public class PojoInputDTO<T extends CommonInputData> extends CommonInputDTO {

    private T data;
}
