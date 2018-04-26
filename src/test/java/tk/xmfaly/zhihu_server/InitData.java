package tk.xmfaly.zhihu_server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.xmfaly.zhihu_server.entity.Fence;
import tk.xmfaly.zhihu_server.entity.FencePoint;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.FencePointRepository;
import tk.xmfaly.zhihu_server.repository.FenceRepository;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;

import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitData {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private FencePointRepository fencePointRepository;

    @Autowired
    private FenceRepository fenceRepository;

    @Test
    public void clear() {
        UserInfo userInfo = userInfoRepository.findOne(1);
        Set<Fence> fences = userInfo.getFences();
        userInfo.setFences(null);
        userInfoRepository.save(userInfo);

        Iterator<Fence> fenceIterator = fences.iterator();
        while (fenceIterator.hasNext()) {
            Fence f = fenceIterator.next();
            Set<FencePoint> fencePoints = f.getFencePoints();
            f.setFencePoints(null);
            fenceRepository.save(f);
            Iterator<FencePoint> fencePointIterator = fencePoints.iterator();
            while (fencePointIterator.hasNext()) {
                FencePoint fencePoint = fencePointIterator.next();
                fencePoint.setFence(null);
                fencePointRepository.save(fencePoint);
                fencePointRepository.delete(fencePoint.getId());
            }
            f.setUserinfo(null);
            fenceRepository.save(f);
            fenceRepository.delete(f.getId());
        }

    }

    @Test
    public void initFence() {
        UserInfo userInfo = userInfoRepository.findOne(1);

        Fence fence = new Fence();

        FencePoint fencePoint = new FencePoint("100.1","100.1",fence);
        FencePoint fencePoint2 = new FencePoint("200.1","100.1",fence);
        FencePoint fencePoint3 = new FencePoint("100.1","200.1",fence);
        FencePoint fencePoint4 = new FencePoint("200.1","200.1",fence);
        fence.getFencePoints().add(fencePoint);
        fence.getFencePoints().add(fencePoint2);
        fence.getFencePoints().add(fencePoint3);
        fence.getFencePoints().add(fencePoint4);
        fenceRepository.save(fence);

        fence.setUserinfo(userInfo);
        userInfo.getFences().add(fence);
        userInfoRepository.save(userInfo);
    }
}
