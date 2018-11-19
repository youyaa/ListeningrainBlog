package listeningrain.cn.blog.service;

import listeningrain.cn.blog.atomservice.AtomUserShowInFormationService;
import listeningrain.cn.blog.constant.ReturnErrCodeEnum;
import listeningrain.cn.blog.entity.UserShowInformation;
import listeningrain.cn.blog.exception.BlogServiceException;
import listeningrain.cn.blog.input.data.UserShowInformationInputData;
import listeningrain.cn.blog.input.dto.PojoInputDTO;
import listeningrain.cn.blog.output.data.UserShowInformationOutputData;
import listeningrain.cn.blog.output.dto.PojoOutputDTO;
import listeningrain.cn.blog.service.api.UserShowInformationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/12 17:33
 * Description:
 */
@Service("userShowInformationService")
public class UserShowInformationServiceImpl implements UserShowInformationService {

    @Autowired
    private AtomUserShowInFormationService atomUserShowInFormationService;

    @Override
    public PojoOutputDTO<UserShowInformationOutputData> getUserShowInformation() {
        UserShowInformation usersById = atomUserShowInFormationService.getUsersById();
        PojoOutputDTO<UserShowInformationOutputData> pojoOutputDTO = null;
        if(null != usersById){
            pojoOutputDTO = new PojoOutputDTO<>();
            UserShowInformationOutputData userShowInformationOutputData = new UserShowInformationOutputData();
            BeanUtils.copyProperties(usersById,userShowInformationOutputData);
            pojoOutputDTO.setData(userShowInformationOutputData);
        }
        return pojoOutputDTO;
    }

    @Override
    public PojoOutputDTO addUserShowInformation(UserShowInformationInputData userShowInformationInputData) {
        UserShowInformation userShowInformation = new UserShowInformation();
        if(null != userShowInformation){
            BeanUtils.copyProperties(userShowInformationInputData,userShowInformation);
        }
        Integer i = atomUserShowInFormationService.insertUser(userShowInformation);
        if(i <= 0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_INSERT);
        }
        return new PojoOutputDTO();
    }

    @Override
    public PojoOutputDTO updateUsershowInformation(PojoInputDTO<UserShowInformationInputData> pojoInputDTO) {
        UserShowInformation userShowInformation = new UserShowInformation();
        if(null != pojoInputDTO){
            BeanUtils.copyProperties(pojoInputDTO.getData(),userShowInformation);
        }
        Integer i = atomUserShowInFormationService.updateUser(userShowInformation);
        if(i <= 0){
            throw new BlogServiceException(ReturnErrCodeEnum.SQL_EXCEPTION_UPDATE);
        }
        return new PojoOutputDTO();
    }
}
