package listeningrain.cn.blog.input.dto;

import listeningrain.cn.blog.input.data.CommonInputData;
import lombok.Getter;
import lombok.Setter;

/**
 * author: listeningrain
 * Date: 2018/9/24
 * Time: 10:04
 * Description: 用于分页查询的公共dto
 */
@Getter
@Setter
public class PageInputDTO<T extends CommonInputData> extends CommonInputDTO{

    /**
     * 默认从第一页开始查，查十条数据
     */
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    //具体封装对象
    private T data;

}
