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

    private Integer errno;
    private T data;

    public ReturnMessage() {
    }

    public ReturnMessage(Integer errno, T data) {
        this.errno = errno;
        this.data = data;
    }
}
