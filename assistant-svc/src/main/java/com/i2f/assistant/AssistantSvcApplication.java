package com.i2f.assistant;

import i2f.springboot.application.WarBootApplication;
import i2f.springboot.asyn.EnableAsyncConfig;
import i2f.springboot.context.EnableSpringContextConfig;
import i2f.springboot.cors.EnableCorsConfig;
import i2f.springboot.exception.EnableExceptionHandlerConfig;
import i2f.springboot.mvc.EnableMvcConfig;
import i2f.springboot.mybatis.EnableMybatisConfig;
import i2f.springboot.property.EnablePropertyDecryptConfig;
import i2f.springboot.redis.EnableRedisConfig;
import i2f.springboot.schedule.EnableScheduleConfig;
import i2f.springboot.secure.EnableSecureConfig;
import i2f.springboot.security.EnableSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@EnableSpringContextConfig
@EnableMvcConfig
@EnableSecureConfig
@EnableSecurityConfig
@EnableRedisConfig
@EnableAsyncConfig
@EnableCorsConfig
@EnableExceptionHandlerConfig
@EnableMybatisConfig
@EnablePropertyDecryptConfig
@EnableScheduleConfig
public class AssistantSvcApplication extends WarBootApplication {

    public static void main(String[] args) {
        String logs = startup(AssistantSvcApplication.class, args);
        log.info(logs);
    }

}
