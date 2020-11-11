package cn.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;

/**
 * @author shanjianfei
 * @create 2020-10-15 15:04
 */
@SpringBootApplication
@MapperScan("cn.core.mapper")
public class JoyStageApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoyStageApplication.class,args);
    }

}
