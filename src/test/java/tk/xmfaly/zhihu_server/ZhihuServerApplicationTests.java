package tk.xmfaly.zhihu_server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.xmfaly.zhihu_server.entity.Fence;
import tk.xmfaly.zhihu_server.entity.FencePoint;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;

import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZhihuServerApplicationTests {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Test
	public void contextLoads() {

	}

}
