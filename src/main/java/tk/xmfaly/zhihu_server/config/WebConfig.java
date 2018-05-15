package tk.xmfaly.zhihu_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;


@Configuration
public class WebConfig {

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