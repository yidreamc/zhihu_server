package tk.xmfaly.zhihu_server.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Fence;
import tk.xmfaly.zhihu_server.entity.FencePoint;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.FencePointRepository;
import tk.xmfaly.zhihu_server.repository.FenceRepository;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;

import java.util.Map;

@RestController
@RequestMapping("/fence")
public class FenceController {

    @Autowired
    private FenceRepository fenceRepository;

    @Autowired
    private FencePointRepository fencePointRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping("/addfence")
    public Object addfence(@RequestBody Map<String, Object> param) {
        int uid = Integer.valueOf(param.get("uid").toString());

        String a =  param.get("points").toString();

        Gson gson = new Gson();
        FencePoint[] points = gson.fromJson(param.get("points").toString(),FencePoint[].class) ;


        try {
            Fence fence = new Fence();
            for (int i = 0; i < points.length; i++) {
                fence.getFencePoints().add(points[i]);
                points[i].setFence(fence);
            }
            fenceRepository.save(fence);
            UserInfo userInfo = userInfoRepository.findOne(uid);
            userInfo.getFences().add(fence);
            fence.setUserinfo(userInfo);
            userInfoRepository.save(userInfo);
            return new Response(0, "success");
        } catch (Exception e) {
            return new Response(1, "error");
        }
    }
}
