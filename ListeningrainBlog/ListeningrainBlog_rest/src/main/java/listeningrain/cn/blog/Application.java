package listeningrain.cn.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:06
 * Description:Rest工程启动类
 */
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource({"classpath:application-consumer.xml"})
public class Application {

    public static void main(String[] args){
        System.out.println("开始启动listeningrainBlog-rest模块");
        SpringApplication.run(Application.class);
        System.out.println("启动listeningrainBlog-rest模块成功");
    }
}
