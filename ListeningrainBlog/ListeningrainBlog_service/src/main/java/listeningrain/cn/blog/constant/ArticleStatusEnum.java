package listeningrain.cn.blog.constant;

/**
 * author: listeningrain
 * Date: 2018/10/25
 * Time: 20:43
 * Description: 文章状态的枚举类
 */
public enum  ArticleStatusEnum {

    PUBLISH("publish","已发布"),
    DRAFT("draft","草稿");


   ArticleStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    //从code获取msg
    public static String getMsg(String code){
        for(ArticleStatusEnum articleStatusEnum : ArticleStatusEnum.values()){
            if(code.equals(articleStatusEnum.code)){
                return articleStatusEnum.getMsg();
            }
        }
        return null;
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
