package listeningrain.cn.blog;

import listeningrain.cn.blog.utils.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:06
 * Description:Rest工程启动类
 */
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource({"classpath:application-consumer.xml"})
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args){
        printToConsole("开始启动listeningrainBlog-rest模块");
        SpringApplication.run(Application.class);
        printToConsole("启动listeningrainBlog-rest模块成功");
    }

    private static void printToConsole(String s) {
        if (null != s && s.length() > 0) {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            System.out.println(time + " " + s);
        } else {
            System.out.println(s);
        }
    }
}
