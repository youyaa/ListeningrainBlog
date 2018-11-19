package listeningrain.cn.blog.atomservice.impl;

import listeningrain.cn.blog.atomservice.AtomUserShowInFormationService;
import listeningrain.cn.blog.dao.UserShowInformationMapper;
import listeningrain.cn.blog.entity.UserShowInformation;
import listeningrain.cn.blog.entity.UserShowInformationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/12 17:24
 * Description:
 */
@Service("atomUserShowInformation")
public class AtomUserShowInFormationServiceImpl implements AtomUserShowInFormationService {

    @Autowired
    private UserShowInformationMapper userShowInformationMapper;

    @Override
    public UserShowInformation getUsersById() {

        List<UserShowInformation> userShowInformations = userShowInformationMapper.selectByExample(new UserShowInformationExample());
        if(null != userShowInformations && 0 < userShowInformations.size()){
            return userShowInformations.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Integer insertUser(UserShowInformation userShowInformation) {
        return userShowInformationMapper.insertSelective(userShowInformation);
    }

    @Override
    @Transactional
    public Integer updateUser(UserShowInformation userShowInformation) {
        int i = userShowInformationMapper.updateByPrimaryKeySelective(userShowInformation);
        return i;
    }
}
