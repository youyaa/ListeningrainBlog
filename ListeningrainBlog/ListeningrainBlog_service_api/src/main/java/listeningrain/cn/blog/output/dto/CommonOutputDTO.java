package listeningrain.cn.blog.output.dto;

import java.io.Serializable;

/**
 * author: listeningrain
 * Date: 2018/9/24
 * Time: 10:26
 * Description: 出参封装对象父类
 */
public class CommonOutputDTO implements Serializable {
    public String code = "SOA0000";
    public String msg = "操作成功";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
