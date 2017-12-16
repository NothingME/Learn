package mine.zhan;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Creator: zhanqian 17/12/15 下午9:55
 * Description: jpa配置，亦可以将注解放到Application.java
 */
@EnableJpaRepositories(basePackages = "mine.zhan.dao")
@EntityScan(basePackages = "mine.zhan.model")
public class JpaConfig {
}
