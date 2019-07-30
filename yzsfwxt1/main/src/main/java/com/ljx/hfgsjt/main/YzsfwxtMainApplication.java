package com.ljx.hfgsjt.main;


//import com.ljx.hfgsjt.util.utils.SpringContextHolder;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = {"com.ljx.hfgsjt"})
@EnableJpaRepositories(basePackages = {"com.ljx.hfgsjt"})
@EntityScan(basePackages = "com.ljx.hfgsjt")
@MapperScan(basePackages= {"com.ljx.hfgsjt.main.dao","com.ljx.hfgsjt.sjst.mapper","com.ljx.hfgsjt.zlgd.mapper"
        ,"com.ljx.hfgsjt.xkybz.dao","com.ljx.hfgsjt.xqsl.dao","com.ljx.hfgsjt.sfkp.dao","com.ljx.hfgsjt.wtys.dao","com.ljx.hfgsjt.gcpd.dao"
        ,"com.ljx.hfgsjt.sgjg.dao","com.ljx.hfgsjt.htqd.dao","com.ljx.hfgsjt.repository.dao"
        ,"com.ljx.hfgsjt.zhys.mapper","com.ljx.hfgsjt.yj.dao","com.ljx.hfgsjt.message.dao","com.ljx.hfgsjt.zhcx.mapper"
        ,"com.ljx.hfgsjt.xmlct.mapper" ,"com.ljx.hfgsjt.js.mapper"})
public class YzsfwxtMainApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(YzsfwxtMainApplication.class);

    public static void main(String[] args){
//        ApplicationContext applicationContext = SpringApplication.run(YzsfwxtMainApplication.class, args);
//        SpringContextHolder.setApplicationContext(applicationContext);
        SpringApplication.run(YzsfwxtMainApplication.class, args);
    }

    // 继承SpringBootServletInitializer 实现configure 方便打war 外部服务器部署。
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YzsfwxtMainApplication.class);
    }

}
