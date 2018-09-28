package listeningrain.cn.blog.output.dto;

import listeningrain.cn.blog.output.data.CommonOutputData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/9/24
 * Time: 10:28
 * Description:
 */
@Getter
@Setter
public class PageOutputDTO<T extends CommonOutputData> extends CommonOutputDTO {

    private Integer pageNum;
    private Integer pageSize;
    private Integer total;

    private List<T> data;

    //存储分页信息
    private Integer[] pageBar;
}
