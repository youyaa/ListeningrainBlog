package listeningrain.cn.blog;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * author: listeningrain
 * Date: 2018/9/16
 * Time: 15:30
 * Description: service模块启动类
 */

public class Bootstrap {

    public static void main(String[] args){

        printToConsole("开始启动listeningrain-service模块");

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        applicationContext.registerShutdownHook();
        applicationContext.start();

        printToConsole("启动listeningrain-service模块成功");

        while (true){
            try {
                TimeUnit.SECONDS.sleep(10000);
            }catch (Exception e){

            }
        }
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
