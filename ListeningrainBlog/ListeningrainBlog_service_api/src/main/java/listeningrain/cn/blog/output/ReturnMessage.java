package listeningrain.cn.blog.output;

import listeningrain.cn.blog.output.data.CommonOutputData;
import lombok.Getter;
import lombok.Setter;

/**
 * author: listeningrain
 * Date: 2018/10/5
 * Time: 17:09
 * Description:
 */
@Setter
@Getter
public class ReturnMessage<T> extends CommonOutputData {

    private Integer status;
    private T msg;

    public ReturnMessage() {
    }

    public ReturnMessage(Integer status, T msg) {
        this.status = status;
        this.msg = msg;
    }
}
