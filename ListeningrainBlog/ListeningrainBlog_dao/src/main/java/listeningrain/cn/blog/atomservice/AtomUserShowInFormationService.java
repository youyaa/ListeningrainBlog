package listeningrain.cn.blog.atomservice;

import listeningrain.cn.blog.entity.UserShowInformation;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/12 17:24
 * Description:
 */
public interface AtomUserShowInFormationService {

    //获取用户信息，一般情况下该表中只存在一条记录
    UserShowInformation getUsersById();

    Integer insertUser(UserShowInformation userShowInformation);

    Integer updateUser(UserShowInformation userShowInformation);

}
