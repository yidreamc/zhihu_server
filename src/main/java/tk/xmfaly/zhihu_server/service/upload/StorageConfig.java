package tk.xmfaly.zhihu_server.service.upload;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfig {
    CommandLineRunner init(StorageService storageService){
        return (args)->{
            storageService.deleteAll();
            storageService.init();
        };

    }
}
