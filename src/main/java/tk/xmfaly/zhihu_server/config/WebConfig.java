package tk.xmfaly.zhihu_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Bean
    public ServerSocket getServerSocket() {
        try {
            return new ServerSocket(8082);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}