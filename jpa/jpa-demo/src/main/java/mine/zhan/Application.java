package mine.zhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Creator: zhanqian 17/12/15 下午9:44
 * Description: 启动类
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"mine.zhan"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
