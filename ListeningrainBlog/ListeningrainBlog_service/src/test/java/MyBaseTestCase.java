import listeningrain.cn.blog.atomservice.AtomContensService;
import listeningrain.cn.blog.entity.Contents;
import listeningrain.cn.blog.input.data.AdminInputData;
import listeningrain.cn.blog.service.api.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:44
 * Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class MyBaseTestCase {

    @Autowired
    private AtomContensService atomContensService;
    @Autowired
    private UsersService usersService;

    @Test
    public void test(){
        List<Contents> contentsByPage = atomContensService.getContentsByPage();
        System.out.println(contentsByPage);
    }

    @Test
    public void testDao(){
        AdminInputData users = new AdminInputData();
        users.setUsername("哇哈哈哈111");
        usersService.insertAdmin(users);
    }


}

