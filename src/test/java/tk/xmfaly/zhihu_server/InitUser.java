package tk.xmfaly.zhihu_server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;

import java.io.IOException;
import java.net.ServerSocket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitUser {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void initUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("yang");

        userInfo.setPassWord(new BCryptPasswordEncoder().encode("yang"));
        userInfoRepository.save(userInfo);
    }

}
