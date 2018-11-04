package listeningrain.cn.blog.exception;

import listeningrain.cn.blog.constant.ReturnErrCodeEnum;

/**
 * author: listeningrain
 * Date: 2018/11/3
 * Time: 16:19
 * Description:
 */
public class BlogServiceException extends RuntimeException {
   private String code;
   private String msg;

   public BlogServiceException(String code, String msg){
       this.code = code;
       this.msg = msg;
   }

   public BlogServiceException(ReturnErrCodeEnum returnErrCodeEnum){
       this.code = returnErrCodeEnum.getErrCode();
       this.msg = returnErrCodeEnum.getMsg();
   }


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
