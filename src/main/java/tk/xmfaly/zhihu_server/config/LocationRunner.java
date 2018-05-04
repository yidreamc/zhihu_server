package tk.xmfaly.zhihu_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tk.xmfaly.zhihu_server.service.LocalcationService;
import tk.xmfaly.zhihu_server.service.SockService;
import tk.xmfaly.zhihu_server.uitl.ApplicationContextProvider;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationRunner implements CommandLineRunner{


    @Autowired
    private SockService sockService;

    @Override
    public void run(String... strings) throws Exception {
        List<LocalcationService> list = new ArrayList<>();
        LocalcationService.OnDisconnectedListener listener = new LocalcationService.OnDisconnectedListener() {
            @Override
            public void onDisonnected(LocalcationService connection) {
                list.remove(connection);
            }
        };
        try {
            while (true) {
                SockService.setSocket(sockService.getServerSocket().accept());
                LocalcationService t = ApplicationContextProvider.getBean("location", LocalcationService.class);
                list.add(t);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
