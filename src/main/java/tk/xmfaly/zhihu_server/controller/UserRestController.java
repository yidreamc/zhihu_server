package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Equipment;
import tk.xmfaly.zhihu_server.entity.Fence;
import tk.xmfaly.zhihu_server.entity.FencePoint;
import tk.xmfaly.zhihu_server.entity.UserInfo;
import tk.xmfaly.zhihu_server.repository.EquipmentRepository;
import tk.xmfaly.zhihu_server.repository.FencePointRepository;
import tk.xmfaly.zhihu_server.repository.FenceRepository;
import tk.xmfaly.zhihu_server.repository.UserInfoRepository;
import tk.xmfaly.zhihu_server.security.JwtTokenUtil;
import tk.xmfaly.zhihu_server.security.JwtUser;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private FenceRepository fenceRepository;

    @Autowired
    private FencePointRepository fencePointRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Response getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserInfo user = userInfoRepository.findByUserName(username);
        Equipment equipment = equipmentRepository.findFirstByUserInfoId(user.getId());

        Fence fence = fenceRepository.findByUserinfoId(user.getId());

        Iterable<FencePoint> fencePoints;
        if(fence != null){
            fencePoints = fencePointRepository.findByFenceId(fence.getId());
        }else {
            fencePoints = null;
        }

        Map<String,Object> res = new HashMap<>();
        res.put("id",user.getId());
        res.put("userName",user.getUserName());
        res.put("tel",user.getTel());
        res.put("age",user.getAge());
        res.put("addr",user.getAddr());
        res.put("remark",user.getRemarks());
        res.put("fence",fencePoints);
        res.put("equipment",equipment);


        return new Response(0,"success",res);
    }

}
