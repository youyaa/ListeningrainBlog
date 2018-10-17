package listeningrain.cn.blog.service;

import listeningrain.cn.blog.atomservice.AtomUsersService;
import listeningrain.cn.blog.entity.Users;
import listeningrain.cn.blog.input.data.AdminInputData;
import listeningrain.cn.blog.output.data.AdminOutputData;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * author: listeningrain
 * Date: 2018/10/4
 * Time: 11:06
 * Description:
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private AtomUsersService atomUsersService;

    @Override
    public PojoOutputDTO<AdminOutputData> getAdmin(AdminInputData adminInputData) {
        Users users = new Users();
        String username = adminInputData.getUsername();
        String password = adminInputData.getPassword();
        users.setUsername(username);
        Base64.Encoder encoder = Base64.getEncoder();
        String encodePassword = encoder.encodeToString((username + password).getBytes());
        users.setPassword(encodePassword);
        Users users1 = atomUsersService.getUsers(users);
        if(null != users1){
            PojoOutputDTO<AdminOutputData> pojoOutputDTO = new PojoOutputDTO<>();
            AdminOutputData adminOutputData = new AdminOutputData();
            BeanUtils.copyProperties(users1,adminOutputData);
            pojoOutputDTO.setData(adminOutputData);
            return pojoOutputDTO;
        }
        return null;
    }
}
