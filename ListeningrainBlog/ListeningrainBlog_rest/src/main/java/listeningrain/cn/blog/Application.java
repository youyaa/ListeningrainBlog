package listeningrain.cn.blog;

import listeningrain.cn.blog.utils.StorageProperties;
import listeningrain.cn.blog.utils.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args){
        System.out.println("开始启动listeningrainBlog-rest模块");
        SpringApplication.run(Application.class);
        System.out.println("启动listeningrainBlog-rest模块成功");
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
