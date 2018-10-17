package listeningrain.cn.blog.atomservice.impl;

import listeningrain.cn.blog.atomservice.AtomUsersService;
import listeningrain.cn.blog.dao.UsersMapper;
import listeningrain.cn.blog.entity.Users;
import listeningrain.cn.blog.entity.UsersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/10/4
 * Time: 10:59
 * Description:
 */
@Service("atomUsersService")
public class AtomUsersServiceImpl implements AtomUsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getUsers(Users users) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUsernameEqualTo(users.getUsername()).andPasswordEqualTo(users.getPassword());
        List<Users> admins = usersMapper.selectByExample(usersExample);
        if(null != admins && 0< admins.size()){
            return admins.get(0);
        }
        return null;
    }
}
