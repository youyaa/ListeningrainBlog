package listeningrain.cn.blog.output.data;

import lombok.Getter;
import lombok.Setter;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/15 14:19
 * Description: 后台管理首页需要的聚合数据
 */
@Getter
@Setter
public class AdminIndexOutputData extends CommentsOutputData{

    Integer totalCount;
    Integer todayCount;
}
