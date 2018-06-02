package tk.xmfaly.zhihu_server.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class Mkdir {

    @PostConstruct
    public void midir(){
        File f = new File("img");
        if(!f.exists()){
            f.mkdir();
        }
    }
}
