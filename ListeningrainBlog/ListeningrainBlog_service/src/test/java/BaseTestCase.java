import listeningrain.cn.blog.dao.TtestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:44
 * Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class BaseTestCase {

    @Autowired
    private TtestMapper ttestMapper;

    @Test
    public void setTtestMapper(){
        System.out.println(ttestMapper.find());
    }
}
