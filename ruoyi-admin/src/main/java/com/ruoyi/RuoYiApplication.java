package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);

        System.out.println("(♥◠‿◠)ﾉﾞ  高校选课管理系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
        printTxt("start-success.txt");
    }
    private static void printTxt(String fileName) {
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            InputStream inputStream = resource.getInputStream();

            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (Exception e) {
            System.out.println("启动横幅加载失败：" + e.getMessage());
        }
    }
}
