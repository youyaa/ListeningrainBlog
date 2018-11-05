package listeningrain.cn.blog.constant;

/**
 * author: listeningrain
 * Date: 2018/11/3
 * Time: 16:24
 * Description: 返回错误码统一格式
 */
public enum ReturnErrCodeEnum {

    SUCCESS("SOA0000","成功"),

    /**
     * 数据库异常相关的错误码定义
     */
    SQL_EXCEPTION_UPDATE("BIE0000","数据库执行更新操作失败"),
    SQL_EXCEPTION_INSERT("BIE0001","数据库执行插入操作失败");

    private String errCode;
    private String msg;

    ReturnErrCodeEnum(String errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }



    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
