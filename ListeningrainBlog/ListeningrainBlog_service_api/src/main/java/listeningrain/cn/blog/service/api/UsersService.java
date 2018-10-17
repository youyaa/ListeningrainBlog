package listeningrain.cn.blog.service.api;

import listeningrain.cn.blog.input.data.AdminInputData;
import listeningrain.cn.blog.output.data.AdminOutputData;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;

/**
 * author: listeningrain
 * Date: 2018/10/4
 * Time: 11:03
 * Description:
 */
public interface UsersService {

    PojoOutputDTO<AdminOutputData> getAdmin(AdminInputData adminInputData);

}
