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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/fence")
public class FenceController {

    @Autowired
    private FenceRepository fenceRepository;

    @Autowired
    private FencePointRepository fencePointRepository;


    @PostMapping("/addfence")
    public Object addfence(@RequestBody Map<String, Object> param) {
        return set(param);
    }

    @PostMapping("/setfence")
    public Object set(@RequestBody Map<String, Object> param) {
        int uid = Integer.valueOf(param.get("uid").toString());
        Gson gson = new Gson();
        FencePoint[] points = gson.fromJson(param.get("points").toString(), FencePoint[].class);
        try {
            Fence fence = new Fence();
            fence.setUserinfoId(uid);

            //删除原来的围栏
            Fence fence1 = fenceRepository.findByUserinfoId(uid);
            if (fence1 != null) {
                fenceRepository.delete(fence1);
            }


            fence = fenceRepository.save(fence);
            for (int i = 0; i < points.length; i++) {
                points[i].setFenceId(fence.getId());
                fencePointRepository.save(points[i]);
            }
            return new Response(0, "success");
        } catch (Exception e) {
            return new Response(1, "error");
        }
    }
}
