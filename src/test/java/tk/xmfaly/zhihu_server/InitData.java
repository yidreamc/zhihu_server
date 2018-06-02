package tk.xmfaly.zhihu_server;

import com.aliyuncs.exceptions.ClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.xmfaly.zhihu_server.heartbeat.HeartRate;
import tk.xmfaly.zhihu_server.heartbeat.HeartRateRepository;
import tk.xmfaly.zhihu_server.repository.FencePointRepository;
import tk.xmfaly.zhihu_server.repository.FenceRepository;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;
import tk.xmfaly.zhihu_server.service.SendMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitData {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private FencePointRepository fencePointRepository;

    @Autowired
    private FenceRepository fenceRepository;

    @Autowired
    private HeartRateRepository heartRateRepository;


    @Test
    public void initFence() throws ClientException {

        SendMessageService.sendMessage("17866622948","123");
    }

    @Test
    public void init(){



    }
}
