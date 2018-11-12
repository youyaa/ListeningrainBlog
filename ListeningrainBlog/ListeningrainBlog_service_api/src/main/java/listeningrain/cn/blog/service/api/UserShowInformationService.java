package listeningrain.cn.blog.service.api;

import listeningrain.cn.blog.input.data.UserShowInformationInputData;
import listeningrain.cn.blog.output.data.UserShowInformationOutputData;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/12 17:29
 * Description:
 */
public interface UserShowInformationService {

    PojoOutputDTO<UserShowInformationOutputData> getUserShowInformation();

    PojoOutputDTO addUserShowInformation(UserShowInformationInputData userShowInformationInputData);

}
