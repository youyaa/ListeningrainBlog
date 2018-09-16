package listeningrain.cn.blog;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:30
 * Description: service模块启动类
 */

public class Bootstrap {

    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");

        System.out.println("开始启动listeningrain-service模块");

        applicationContext.registerShutdownHook();
        applicationContext.start();

        System.out.println("启动listeningrain-service模块启动成功");

        while (true){
            try {
                TimeUnit.SECONDS.sleep(10000);
            }catch (Exception e){

            }
        }
    }
}
